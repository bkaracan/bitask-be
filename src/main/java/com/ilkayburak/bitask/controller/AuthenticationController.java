package com.ilkayburak.bitask.controller;

import com.ilkayburak.bitask.auth.AuthenticationService;
import com.ilkayburak.bitask.dto.AuthenticationRequestDTO;
import com.ilkayburak.bitask.dto.AuthenticationResponseDTO;
import com.ilkayburak.bitask.dto.RegistrationRequestDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
@Tag(name = "Authentication")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponsePayload<?> register(@RequestBody @Valid RegistrationRequestDTO registrationRequestDTO) throws MessagingException {
        return authenticationService.register(registrationRequestDTO);
    }

    @PostMapping("/authenticate")
    public ResponsePayload<AuthenticationResponseDTO> authenticate(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        return authenticationService.authenticate(authenticationRequestDTO);
    }

    @GetMapping("/activate-account")
    public ResponsePayload<String> confirm(@RequestParam String token) throws MessagingException {
      return authenticationService.activateAccount(token);
    }

}
