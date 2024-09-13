package com.ilkayburak.bitask.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PasswordResetRequestDTO {

    @NotBlank(message = "Password is mandatory!")
    @Size(min = 8, message = "Password must be 8 characters long minimum")
    private String newPassword;
}
