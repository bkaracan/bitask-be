package com.ilkayburak.bitask.service;

import com.ilkayburak.bitask.dto.NotificationDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;

import java.util.List;

public interface NotificationService {

    ResponsePayload<NotificationDTO> save(NotificationDTO notificationDTO);
    ResponsePayload<NotificationDTO> getById(Long id);
    ResponsePayload<List<NotificationDTO>> getAll();


}
