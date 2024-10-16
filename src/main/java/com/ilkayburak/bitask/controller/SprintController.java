package com.ilkayburak.bitask.controller;

import com.ilkayburak.bitask.dto.SprintDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;
import com.ilkayburak.bitask.service.SprintService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sprint")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SprintController {

    private final SprintService sprintService;

    @PostMapping("/save")
    @Operation(summary = "Create or update a sprint")
    ResponsePayload<SprintDTO> save(@RequestBody SprintDTO sprintDTO) {
        return sprintService.save(sprintDTO);
    }

    @GetMapping("/getSprintById")
    @Operation(summary = "Get sprint by ID")
    ResponsePayload<SprintDTO> getSprintById(@RequestParam Long id) {
        return sprintService.getById(id);
    }

    @DeleteMapping("/deleteById")
    @Operation(summary = "Delete sprint by ID")
    ResponsePayload<Void> deleteById(@RequestParam Long id) {
        return sprintService.deleteById(id);
    }

}
