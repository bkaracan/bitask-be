package com.ilkayburak.bitask.controller;

import com.ilkayburak.bitask.dto.NotificationDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;
import com.ilkayburak.bitask.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/save")
    @Operation(summary = "Create or update a notification.")
    public ResponsePayload<NotificationDTO> save(@RequestBody NotificationDTO notificationDTO) {
        return notificationService.save(notificationDTO);
    }

    @GetMapping("/getNotificationById")
    @Operation(summary = "Get notification by ID.")
    public ResponsePayload<NotificationDTO> getNotificationById(@RequestParam("id") Long id) {
        return notificationService.getById(id);
    }

    @GetMapping("/getAllNotifications")
    @Operation(summary = "Get all notifications")
    public ResponsePayload<List<NotificationDTO>> getAllNotifications() {
        return notificationService.getAll();
    }

}
