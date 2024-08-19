package com.ilkayburak.bitask.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequestDTO {

    @NotBlank(message = "First name is mandatory!")
    private String firstName;
    @NotBlank(message = "Last name is mandatory!")
    private String lastName;
    @Column(unique = true)
    @NotBlank(message = "e-mail is mandatory!")
    @Email(message = "email is not formatted!")
    private String email;
    @NotBlank(message = "Password is mandatory!")
    @Size(min = 8, message = "Password must be 8 characters long minimum!")
    private String password;
    @NotBlank(message = "Title is mandatory!")
    private String jobTitle;
    private LocalDate dateOfBirth;
}
