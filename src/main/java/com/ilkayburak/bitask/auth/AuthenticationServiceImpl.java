package com.ilkayburak.bitask.auth;

import com.ilkayburak.bitask.dto.AuthenticationRequestDTO;
import com.ilkayburak.bitask.dto.AuthenticationResponseDTO;
import com.ilkayburak.bitask.dto.PasswordResetRequestDTO;
import com.ilkayburak.bitask.dto.RegistrationRequestDTO;
import com.ilkayburak.bitask.dto.RegistrationResponseDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;
import com.ilkayburak.bitask.email.EmailService;
import com.ilkayburak.bitask.entity.Token;
import com.ilkayburak.bitask.entity.User;
import com.ilkayburak.bitask.enumarations.EmailTemplateNameEnum;
import com.ilkayburak.bitask.enumarations.core.MessageEnum;
import com.ilkayburak.bitask.enumarations.core.ResponseEnum;
import com.ilkayburak.bitask.exception.InvalidTokenException;
import com.ilkayburak.bitask.exception.TokenExpiredException;
import com.ilkayburak.bitask.mapper.UserDTOMapper;
import com.ilkayburak.bitask.repository.JobTitleRepository;
import com.ilkayburak.bitask.repository.RoleRepository;
import com.ilkayburak.bitask.repository.TokenRepository;
import com.ilkayburak.bitask.repository.UserRepository;
import com.ilkayburak.bitask.security.JwtService;
import jakarta.mail.MessagingException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class AuthenticationServiceImpl implements AuthenticationService {

  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final TokenRepository tokenRepository;
  private final JobTitleRepository jobTitleRepository;
  private final EmailService emailService;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;
  private final UserDTOMapper userDTOMapper;

  public ResponsePayload<RegistrationResponseDTO> register(RegistrationRequestDTO registrationRequestDTO)
      throws MessagingException {
    var userRole =
        roleRepository
            .findByName("USER")
            .orElseThrow(() -> new IllegalStateException("Role USER was not initialized!"));
    // jobTitleId ile JobTitle entity'sini buluyoruz
    var jobTitle =
        jobTitleRepository
            .findById(registrationRequestDTO.getJobTitleId())
            .orElseThrow(() -> new IllegalStateException("Job title not found!"));
    // Mapper sınıfında User entity'sini oluşturuyoruz
    var user = userDTOMapper.mapForRegistration(registrationRequestDTO, jobTitle);
    user.setPassword(passwordEncoder.encode(user.getPassword())); // Şifreyi encode ediyoruz
    user.setRoles(List.of(userRole));
    userRepository.save(user);
    sendValidationEmail(user);
    return new ResponsePayload<>(
        ResponseEnum.OK,
        MessageEnum.REGISTRATION_SUCCESS.getMessage(),
        RegistrationResponseDTO.builder().build());
  }

  @Override
  public ResponsePayload<AuthenticationResponseDTO> authenticate(
      AuthenticationRequestDTO authenticationRequestDTO) {
    var auth =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authenticationRequestDTO.getEmail(), authenticationRequestDTO.getPassword()));
    var claims = new HashMap<String, Object>();
    var user = ((User) auth.getPrincipal());
    claims.put("fullName", user.fullName());
    var jwtoken = jwtService.generateToken(claims, user);
    return new ResponsePayload<>(
        ResponseEnum.OK,
        MessageEnum.AUTHENTICATED.getMessage(),
        AuthenticationResponseDTO.builder().token(jwtoken).build());
  }

  @Override
  @Transactional
  public ResponsePayload<String> activateAccount(String token) throws MessagingException {
    Token savedToken =
        tokenRepository
            .findByTokenValue(token)
            .orElseThrow(() -> new InvalidTokenException("Invalid token"));
    if (LocalDateTime.now().isAfter(savedToken.getExpiredAt())) {
      sendValidationEmail(savedToken.getUser());
      throw new TokenExpiredException(
          "Activation token has expired. A new token has been sent to the same email address");
    }
    var user =
        userRepository
            .findById(savedToken.getUser().getId())
            .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    user.setEnabled(true);
    userRepository.save(user);
    savedToken.setValidatedAt(LocalDateTime.now());
    tokenRepository.save(savedToken);
    return new ResponsePayload<>(ResponseEnum.OK, MessageEnum.ACTIVATION_SUCCESS.getMessage());
  }

  @Override
  @Transactional
  public ResponsePayload<String> sendResetPasswordCode(String email, String token) {
    try {
      var user = userRepository.findByEmail(email)
          .orElseThrow(() -> new IllegalStateException("User not found!"));

      if (token == null) {
        String generatedResetCode = generateAndSaveActivationToken(user);

        emailService.sendEmail(
            user.getEmail(),
            user.getFirstName(),
            EmailTemplateNameEnum.RESET_PASSWORD,
            null,
            generatedResetCode,
            "Your Password Reset Code");

        return new ResponsePayload<>(ResponseEnum.OK, "Password reset code has been sent to your email.");
      } else {
        Token savedToken = tokenRepository.findByTokenValue(token)
            .orElseThrow(() -> new InvalidTokenException("Reset code not found!"));

        if (LocalDateTime.now().isAfter(savedToken.getExpiredAt())) {
          // Optionally send a new code
          String newResetCode = generateAndSaveActivationToken(user);
          emailService.sendEmail(
              user.getEmail(),
              user.getFirstName(),
              EmailTemplateNameEnum.RESET_PASSWORD,
              null,
              newResetCode,
              "Your Password Reset Code");

          return new ResponsePayload<>(ResponseEnum.ERROR, "Reset code has expired. A new code has been sent to your email.");
        }

        savedToken.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(savedToken);

        return new ResponsePayload<>(ResponseEnum.OK, "Reset code verified successfully.");
      }
    } catch (InvalidTokenException e) {
      return new ResponsePayload<>(ResponseEnum.ERROR, "Invalid reset code.");
    } catch (Exception e) {
      return new ResponsePayload<>(ResponseEnum.ERROR, "An unexpected error occurred.");
    }
  }



  @Override
  public ResponsePayload<String> resetPassword(String token, PasswordResetRequestDTO passwordResetRequestDTO) {
    Token resetToken = tokenRepository.findByTokenValue(token)
            .orElseThrow(() -> new RuntimeException("Invalid or expired token"));

    if(LocalDateTime.now().isAfter(resetToken.getExpiredAt())) {
      throw new TokenExpiredException("Reset token has expired.");
    }

    User user = resetToken.getUser();
    user.setPassword(passwordEncoder.encode(passwordResetRequestDTO.getNewPassword()));
    userRepository.save(user);

    return  new ResponsePayload<>(ResponseEnum.OK, "Password has been reset successfully.");
  }

  private void sendValidationEmail(User user) throws MessagingException {
    var newToken = generateAndSaveActivationToken(user);
    String confirmationUrl = "http://localhost:8088/api/v1/auth/confirm?token=" + newToken;
    emailService.sendEmail(
        user.getEmail(),
        user.getFirstName(),
        EmailTemplateNameEnum.ACTIVATE_ACCOUNT,
        confirmationUrl,
        newToken,
        "Activate your account");
  }

  private String generateAndSaveActivationToken(User user) {
    String generatedToken = generateActivationCode(6);
    var token =
        Token.builder()
            .tokenValue(generatedToken)
            .createdAt(LocalDateTime.now())
            .expiredAt(LocalDateTime.now().plusMinutes(15))
            .user(user)
            .build();
    tokenRepository.save(token);
    return generatedToken;
  }

  private String generateActivationCode(int length) {
    String characters = "0123456789";
    StringBuilder codeBuilder = new StringBuilder();
    SecureRandom secureRandom = new SecureRandom();
    for (int i = 0; i < length; i++) {
      int randomIndex = secureRandom.nextInt(characters.length());
      codeBuilder.append(characters.charAt(randomIndex));
    }
    return codeBuilder.toString();
  }
}
