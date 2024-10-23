package com.ilkayburak.bitask.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBoardRequestDTO {

  private Long id;
  private String name;
  private List<Long> membersToAdd;
  private List<Long> membersToRemove;

}
