package com.ilkayburak.bitask.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationResponseDTO {

  private String message;
  private String username;
  private String email;
  private String token;
}
