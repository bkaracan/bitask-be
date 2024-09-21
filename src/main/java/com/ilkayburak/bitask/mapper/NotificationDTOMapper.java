package com.ilkayburak.bitask.mapper;

import com.ilkayburak.bitask.dto.NotificationDTO;
import com.ilkayburak.bitask.entity.Notification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationDTOMapper {

    public NotificationDTO convertToDTO(Notification notification) {
        if (notification == null) {
            return null;
        }
        return NotificationDTO.builder()
                .id(notification.getId())
                .message(notification.getMessage())
                .type(notification.getType())
                .userDTO(new UserDTOMapper().convertToDto(notification.getUser()))
                .isRead(notification.getIsRead())
                .createdAt(notification.getCreatedAt())
                .readAt(notification.getReadAt())
                .build();
    }

    public Notification convertToEntity(NotificationDTO notificationDTO) {
        if (notificationDTO == null) {
            return null;
        }
        return Notification.builder()
                .id(notificationDTO.getId())
                .message(notificationDTO.getMessage())
                .type(notificationDTO.getType())
                .user(new UserDTOMapper().convertToEntity(notificationDTO.getUserDTO()))
                .isRead(notificationDTO.getIsRead())
                .createdAt(notificationDTO.getCreatedAt())
                .readAt(notificationDTO.getReadAt())
                .build();
    }

    public List<NotificationDTO> mapList(List<Notification> list) {
        return list.stream().map(this::convertToDTO).toList();
    }

    public List<Notification> mapListToEntity(List<NotificationDTO> list) {
        return list.stream().map(this::convertToEntity).toList();
    }

    public NotificationDTO mapWithoutObject(Notification notification) {
        if (notification == null) {
            return null;
        }
        return NotificationDTO.builder()
                .id(notification.getId())
                .message(notification.getMessage())
                .type(notification.getType())
                .isRead(notification.getIsRead())
                .createdAt(notification.getCreatedAt())
                .readAt(notification.getReadAt())
                .build();
    }

    public List<NotificationDTO> mapListWithoutObject(List<Notification> list) {
        return list.stream().map(this::convertToDTO).toList();
    }
}