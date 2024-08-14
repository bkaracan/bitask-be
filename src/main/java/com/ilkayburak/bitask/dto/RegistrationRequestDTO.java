package com.ilkayburak.bitask.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequestDTO {

    @NotEmpty(message = "First name is mandatory!")
    @NotBlank(message = "First name is mandatory!")
    private String firstName;
    @NotEmpty(message = "Last name is mandatory!")
    @NotBlank(message = "Last name is mandatory!")
    private String lastName;
    @Column(unique = true)
    @NotEmpty(message = "e-mail is mandatory!")
    @NotBlank(message = "e-mail is mandatory!")
    @Email(message = "email is not formatted!")
    private String email;
    @NotEmpty(message = "Password is mandatory!")
    @NotBlank(message = "Password is mandatory!")
    @Size(min = 8, message = "Password must be 8 characters long minimum!")
    private String password;
}
