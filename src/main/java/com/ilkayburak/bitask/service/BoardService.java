package com.ilkayburak.bitask.service;

import com.ilkayburak.bitask.dto.BoardDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;

import java.util.List;

public interface BoardService {

    ResponsePayload<BoardDTO> save(BoardDTO boardDTO);
    ResponsePayload<BoardDTO> getById(Long id);
    ResponsePayload<List<BoardDTO>> getAllBoardsForUser(Long id);
    ResponsePayload<List<BoardDTO>> getAllBoardsByCreator(Long id);
    ResponsePayload<Void> deleteById(Long id);

}
