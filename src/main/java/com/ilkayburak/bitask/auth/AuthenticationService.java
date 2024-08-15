package com.ilkayburak.bitask.auth;

import com.ilkayburak.bitask.dto.AuthenticationRequestDTO;
import com.ilkayburak.bitask.dto.AuthenticationResponseDTO;
import com.ilkayburak.bitask.dto.RegistrationRequestDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;
import jakarta.mail.MessagingException;

public interface AuthenticationService {

    ResponsePayload<?> register(RegistrationRequestDTO registrationRequestDTO) throws MessagingException;

    ResponsePayload<AuthenticationResponseDTO> authenticate(AuthenticationRequestDTO authenticationRequestDTO);
}
