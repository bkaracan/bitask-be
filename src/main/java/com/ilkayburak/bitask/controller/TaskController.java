package com.ilkayburak.bitask.controller;

import com.ilkayburak.bitask.dto.TaskDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;
import com.ilkayburak.bitask.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskController {

    private final TaskService taskService;

    // Creating a task
    @PostMapping("/save")
    @Operation(summary = "Create a task")
    public ResponsePayload<TaskDTO> saveTask(@RequestBody TaskDTO taskDTO) {
        return taskService.save(taskDTO);
    }

    @PostMapping("/update")
    @Operation(summary = "Updates an existing board")
    public ResponsePayload<TaskDTO> update(@RequestBody TaskDTO taskDTO) {
        return taskService.update(taskDTO);
    }

    // Get a task by its unique ID
    @GetMapping("/getTaskById")
    @Operation(summary = "Get a task by its unique ID")
    public ResponsePayload<TaskDTO> getTaskById(@RequestParam Long id) {
        return taskService.getById(id);
    }

    // Get a task by key ID, unique to a board
    @GetMapping("/getTaskByKeyId")
    @Operation(summary = "Get a task by key ID, unique to a board")
    public ResponsePayload<TaskDTO> getTaskByKeyId(@RequestParam String keyId, @RequestParam Long boardId) {
        return taskService.getByKeyIdAndBoardId(keyId, boardId);
    }

    // Get all task in a board
    @GetMapping("/getAllTasksByBoardId")
    @Operation(summary = "Get all task in a board")
    public ResponsePayload<List<TaskDTO>> getAllTasksByBoardId(@RequestParam("boardId") Long boardId) {
        return taskService.getAllTaskByBoardId(boardId);
    }

    // Get all tasks by its status in a board
    @GetMapping("/getAllTasksByStatusAndBoardId")
    @Operation(summary = "Get all tasks by its status in a board")
    public ResponsePayload<List<TaskDTO>> getAllTasksByStatusAndBoardId(@RequestParam("status") Long status, Long boardId) {
        return taskService.getAllTaskByStatusAndBoardId(status, boardId);
    }

    // Get all tasks of a user by status, regardless of boards
    @GetMapping("/getAllTasksByStatusAndUserId")
    @Operation(summary = "Get all tasks of a user by status, regardless of boards")
    public ResponsePayload<List<TaskDTO>> getAllOpenTasksByUserId(@RequestParam Long status, @RequestParam Long userId) {
        return taskService.getAllTaskByStatusAndUserId(status, userId);
    }

    @DeleteMapping("/deleteById")
    @Operation(summary = "Delete a task by ID")
    ResponsePayload<Void> deleteById(@RequestParam Long id) {
        return taskService.deleteById(id);
    }

}
