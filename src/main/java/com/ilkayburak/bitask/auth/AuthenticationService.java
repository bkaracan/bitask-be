package com.ilkayburak.bitask.auth;

import com.ilkayburak.bitask.dto.AuthenticationRequestDTO;
import com.ilkayburak.bitask.dto.AuthenticationResponseDTO;
import com.ilkayburak.bitask.dto.PasswordResetRequestDTO;
import com.ilkayburak.bitask.dto.RegistrationRequestDTO;
import com.ilkayburak.bitask.dto.RegistrationResponseDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;
import jakarta.mail.MessagingException;

public interface AuthenticationService {

    ResponsePayload<RegistrationResponseDTO> register(RegistrationRequestDTO registrationRequestDTO) throws MessagingException;

    ResponsePayload<AuthenticationResponseDTO> authenticate(AuthenticationRequestDTO authenticationRequestDTO);

    ResponsePayload<String> activateAccount(String token) throws MessagingException;

    ResponsePayload<String> sendResetPasswordCode(String email) throws MessagingException;

    ResponsePayload<String> resetPassword(String token, PasswordResetRequestDTO passwordResetRequestDTO);
}
