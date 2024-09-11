package com.ilkayburak.bitask.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentsUsersDTO {

    private Long id;
    private CommentsDTO comment;
    private UserDTO user;

}
