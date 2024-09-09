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
public class TaskDTO {

    Long id;
    String keyId;
    private String title;
    private String description;
    private Long priority;
    private Long status;
    private LocalDateTime createdDate;
    private LocalDate expectedFinishDate;
    private LocalDate deadline;
    private BoardDTO board;
    private List<UserDTO> assignees;
    private Long blockingTask;

}