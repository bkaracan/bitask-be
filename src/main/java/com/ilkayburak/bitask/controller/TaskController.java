package com.ilkayburak.bitask.controller;

import com.ilkayburak.bitask.dto.TaskDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;
import com.ilkayburak.bitask.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/saveTask")
    public ResponsePayload<TaskDTO> saveTask(@RequestBody TaskDTO taskDTO) {
        return taskService.save(taskDTO);
    }

    @GetMapping("/getTaskById")
    public ResponsePayload<TaskDTO> getTaskById(@RequestParam Long id) {
        return taskService.getById(id);
    }

    @GetMapping("/getTaskByKeyId")
    public ResponsePayload<TaskDTO> getTaskByKeyId(@RequestParam String keyId, @RequestParam Long boardId) {
        return taskService.getByKeyIdAndBoardId(keyId, boardId);
    }

    @GetMapping("/getAllTasksByBoardId")
    public ResponsePayload<List<TaskDTO>> getAllTasksByBoardId(@RequestParam("boardId") Long boardId) {
        return taskService.getAllTaskByBoardId(boardId);
    }

    @GetMapping("/getAllTasksByStatusAndBoardId")
    public ResponsePayload<List<TaskDTO>> getAllTasksByStatusAndBoardId(@RequestParam("status") Long status, Long boardId) {
        return taskService.getAllTaskByStatusAndBoardId(status, boardId);
    }

    @GetMapping("/getAllTasksByStatusAndUserId")
    public ResponsePayload<List<TaskDTO>> getAllOpenTasksByUserId(@RequestParam Long status, @RequestParam Long userId) {
        return taskService.getAllTaskByStatusAndUserId(status, userId);
    }

}
