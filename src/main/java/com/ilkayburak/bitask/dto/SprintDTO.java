package com.ilkayburak.bitask.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class SprintDTO {

    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long status;
    private List<TaskDTO> task;
    private BoardDTO board;

}
