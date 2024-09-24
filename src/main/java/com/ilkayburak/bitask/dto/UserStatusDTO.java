package com.ilkayburak.bitask.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UserStatusDTO {

    private Long id;
    private String name;
    List<UserDTO> users;

}
