package com.ilkayburak.bitask.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JobTitleDTO {

  private Long id;
  private String name;
  List<UserDTO> users;
}
