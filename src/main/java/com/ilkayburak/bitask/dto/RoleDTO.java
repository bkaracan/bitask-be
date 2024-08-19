package com.ilkayburak.bitask.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class RoleDTO {

  private Long id;
  private String name;
  private List<Long> userIds; // User entity'si ile olan ilişkiyi userId'ler üzerinden göstereceğiz
  private LocalDateTime createdDate;
  private LocalDateTime lastModifiedDate;
}
