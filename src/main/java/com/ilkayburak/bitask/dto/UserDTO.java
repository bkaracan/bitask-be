package com.ilkayburak.bitask.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {

  private Long id;
  private String firstName;
  private String lastName;
  private LocalDate dateOfBirth;
  private String email;
  private String password;
  private JobTitleDTO jobTitle;
  private UserStatusDTO userStatus;
  private boolean isAccountLocked;
  private boolean isEnabled;
  private LocalDateTime createdDate;
  private LocalDateTime lastModifiedDate;
  private List<String> roles;
}
