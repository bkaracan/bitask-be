package com.ilkayburak.bitask.controller;

import com.ilkayburak.bitask.auth.AuthenticationService;
import com.ilkayburak.bitask.dto.AuthenticationRequestDTO;
import com.ilkayburak.bitask.dto.AuthenticationResponseDTO;
import com.ilkayburak.bitask.dto.PasswordResetRequestDTO;
import com.ilkayburak.bitask.dto.RegistrationRequestDTO;
import com.ilkayburak.bitask.dto.RegistrationResponseDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;
import com.ilkayburak.bitask.enumarations.core.MessageEnum;
import com.ilkayburak.bitask.enumarations.core.ResponseEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
@Tag(name = "Authentication")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponsePayload<RegistrationResponseDTO> register(@RequestBody @Valid RegistrationRequestDTO registrationRequestDTO) throws MessagingException {
        return authenticationService.register(registrationRequestDTO);
    }

    @PostMapping("/authenticate")
    public ResponsePayload<AuthenticationResponseDTO> authenticate(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        try {
            ResponsePayload<AuthenticationResponseDTO> responsePayload = authenticationService.authenticate(authenticationRequestDTO);
      return new ResponsePayload<>(
          ResponseEnum.OK,
          MessageEnum.AUTHENTICATED.getMessage(),
          AuthenticationResponseDTO.builder()
              .token(responsePayload.getData().getToken())
              .build());
        } catch (BadCredentialsException ex) {
            return new ResponsePayload<>(ResponseEnum.UNAUTHORIZED, MessageEnum.BAD_CREDENTIALS.getMessage());
        }
    }

    @GetMapping("/activate-account")
    public ResponsePayload<String> confirm(@RequestParam String token) throws MessagingException {
      return authenticationService.activateAccount(token);
    }

    @PostMapping("/forgot-password")
    public ResponsePayload<String> forgotPassword(@RequestParam String email) throws MessagingException {
        return authenticationService.sendResetPasswordCode(email);
    }

    @PostMapping("/reset-password")
    public ResponsePayload<String> resetPassword(@RequestParam String token, @RequestBody @Valid PasswordResetRequestDTO passwordResetRequestDTO) {
        return authenticationService.resetPassword(token, passwordResetRequestDTO);
    }

}
