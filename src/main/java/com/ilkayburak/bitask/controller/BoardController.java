package com.ilkayburak.bitask.controller;

import com.ilkayburak.bitask.dto.BoardDTO;
import com.ilkayburak.bitask.dto.CreateBoardRequestDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;
import com.ilkayburak.bitask.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("board")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/save")
    @Operation(summary = "Creates a new board")
    public ResponsePayload<BoardDTO> save(@RequestBody CreateBoardRequestDTO createBoardRequestDTO) {
        return boardService.save(createBoardRequestDTO);
    }

    @PostMapping("/update")
    @Operation(summary = "Updates an existing board")
    public ResponsePayload<BoardDTO> update(@RequestBody BoardDTO boardDTO) {
        return boardService.update(boardDTO);
    }

    @GetMapping("/getBoardById")
    @Operation(summary = "Get board by ID")
    public ResponsePayload<BoardDTO> getBoardById(@RequestParam Long id) {
        return boardService.getById(id);
    }

    @GetMapping("/getAllBoardsForUser")
    @Operation(summary = "Get all boards that user is in")
    public ResponsePayload<List<BoardDTO>> getAllBoardsForUser(@RequestParam Long id) {
        return boardService.getAllBoardsForUser(id);
    }

    @GetMapping("/getAllBoardsByCreator")
    @Operation(summary = "Get all boards by creator")
    public ResponsePayload<List<BoardDTO>> getAllBoardsByCreator(@RequestParam Long id) {
        return boardService.getAllBoardsByCreator(id);
    }

    @DeleteMapping("/deleteById")
    @Operation(summary = "Delete a board by ID")
    public ResponsePayload<Void> deleteById(@RequestParam Long id) {
        return boardService.deleteById(id);
    }

}
