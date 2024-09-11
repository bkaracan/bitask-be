package com.ilkayburak.bitask.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentsUsersDTO {

    private Long id;
    private Long taskId;
    private Long boardId;
    private UserDTO user;

}
