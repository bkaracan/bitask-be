package com.ilkayburak.bitask.auth;

import com.ilkayburak.bitask.dto.RegistrationRequestDTO;
import com.ilkayburak.bitask.email.EmailService;
import com.ilkayburak.bitask.entity.Token;
import com.ilkayburak.bitask.entity.User;
import com.ilkayburak.bitask.enumarations.EmailTemplateNameEnum;
import com.ilkayburak.bitask.repository.RoleRepository;
import com.ilkayburak.bitask.repository.TokenRepository;
import com.ilkayburak.bitask.repository.UserRepository;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class AuthenticationServiceImpl implements AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;


    public void register(RegistrationRequestDTO registrationRequestDTO) throws MessagingException {
        var userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("Role USER was not initialized!"));
        var user = User.builder()
                .firstName(registrationRequestDTO.getFirstName())
                .lastName(registrationRequestDTO.getLastName())
                .email(registrationRequestDTO.getEmail())
                .password(passwordEncoder.encode(registrationRequestDTO.getPassword()))
                .isAccountLocked(false)
                .isEnabled(false)
                .roles(List.of(userRole))
                .build();
        userRepository.save(user);
        sendValidationEmail(user);
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
        var token = Token.builder()
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
        for(int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }
}
