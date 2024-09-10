package com.ilkayburak.bitask.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

  @NotNull(message = "Job title id is mandatory!")
  private Long jobTitleId;

  private LocalDate dateOfBirth;

  public String getFormattedName(String name) {
    String[] words = name.toLowerCase().split(" ");
    StringBuilder capitalizedStr = new StringBuilder();

    for (String word : words) {
      if (!word.isEmpty()) {
        capitalizedStr
            .append(word.substring(0, 1).toUpperCase())
            .append(word.substring(1))
            .append(" ");
      }
    }

    return capitalizedStr.toString().trim();
  }
}
