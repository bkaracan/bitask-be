package com.ilkayburak.bitask.auth;

import com.ilkayburak.bitask.dto.RegistrationRequestDTO;
import jakarta.mail.MessagingException;

public interface AuthenticationService {

    public void register(RegistrationRequestDTO registrationRequestDTO) throws MessagingException;
}
