package com.ilkayburak.bitask.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CommentsDTO {

    private Long id;
    private Long taskId;
    private Long boardId;
    private UserDTO user;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
