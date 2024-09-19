package com.ilkayburak.bitask.controller;

import com.ilkayburak.bitask.dto.CommentsDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;
import com.ilkayburak.bitask.service.CommentsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentsController {

    private final CommentsService commentsService;

    @PostMapping("/save")
    @Operation(summary = "Post or update a comment.")
    ResponsePayload<CommentsDTO> save(@RequestBody CommentsDTO commentsDTO) {
        return commentsService.save(commentsDTO);
    }

    @GetMapping("getCommentById")
    @Operation(summary = "Get comment by ID.")
    ResponsePayload<CommentsDTO> getCommentById(@RequestParam Long id) {
        return commentsService.getById(id);
    }

    @GetMapping("/getAllCommentsByTaskId")
    @Operation(summary = "Get all comments of a task by task ID.")
    ResponsePayload<List<CommentsDTO>> getAllCommentsByTaskId(@RequestParam Long taskId) {
        return commentsService.getAllCommentsByTaskId(taskId);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete a task by ID")
    ResponsePayload<Void> delete(@RequestParam Long id) {
        return commentsService.deleteById(id);
    }

}
