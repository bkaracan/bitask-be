package com.ilkayburak.bitask.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class NotificationDTO {

    private Long id;
    private String message;
    private Long type;
    private UserDTO userDTO;
    private Boolean isRead;
    private LocalDateTime createdAt;
    private LocalDate readAt;

}
