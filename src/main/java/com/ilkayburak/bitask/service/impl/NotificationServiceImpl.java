package com.ilkayburak.bitask.service.impl;

import com.ilkayburak.bitask.dto.NotificationDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;
import com.ilkayburak.bitask.entity.Notification;
import com.ilkayburak.bitask.enumarations.core.MessageEnum;
import com.ilkayburak.bitask.enumarations.core.ResponseEnum;
import com.ilkayburak.bitask.mapper.NotificationDTOMapper;
import com.ilkayburak.bitask.repository.NotificationRepository;
import com.ilkayburak.bitask.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationDTOMapper mapper;

    @Override
    public ResponsePayload<NotificationDTO> save(NotificationDTO notificationDTO) {
        Optional<Notification> notification = notificationRepository.findById(notificationDTO.getId());
        if (notification.isEmpty()) {
            return new ResponsePayload<>(ResponseEnum.OK, "Notification sent successfully.",
                    mapper.convertToDTO(notificationRepository.save(mapper.convertToEntity(notificationDTO))));
        }
        return new ResponsePayload<>(ResponseEnum.OK, "Notification updated successfully.",
                mapper.convertToDTO(notificationRepository.save(mapper.convertToEntity(notificationDTO))));
    }

    @Override
    public ResponsePayload<NotificationDTO> getById(Long id) {
        Optional<Notification> notification = notificationRepository.findById(id);
        if (notification.isPresent()) {
            return new ResponsePayload<>(ResponseEnum.OK, mapper.convertToDTO(notification.get()));
        }
        return new ResponsePayload<>(ResponseEnum.NOTFOUND, MessageEnum.NOT_FOUND.getMessage());
    }

    @Override
    public ResponsePayload<List<NotificationDTO>> getAll() {
        List<Notification> notifications = notificationRepository.findAll();
        if (notifications.isEmpty()) {
            return new ResponsePayload<>(ResponseEnum.BADREQUEST, MessageEnum.EMPTY_LIST.getMessage());
        }
        return new ResponsePayload<>(ResponseEnum.OK, mapper.mapList(notifications));
    }
}
