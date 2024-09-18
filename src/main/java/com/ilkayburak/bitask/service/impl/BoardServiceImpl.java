package com.ilkayburak.bitask.service.impl;

import com.ilkayburak.bitask.dto.BoardDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;
import com.ilkayburak.bitask.entity.Board;
import com.ilkayburak.bitask.enumarations.core.MessageEnum;
import com.ilkayburak.bitask.enumarations.core.ResponseEnum;
import com.ilkayburak.bitask.mapper.BoardDTOMapper;
import com.ilkayburak.bitask.repository.BoardRepository;
import com.ilkayburak.bitask.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final BoardDTOMapper mapper;

    @Override
    public ResponsePayload<BoardDTO> save(BoardDTO boardDTO) {
        Optional<Board> board = boardRepository.findById(boardDTO.getId());
        if (board.isEmpty()) {
            return new ResponsePayload<>(ResponseEnum.OK, "Board created successfully.",
                    mapper.convertToDTO(boardRepository.save(mapper.convertToEntity(boardDTO))));
        }
        return new ResponsePayload<>(ResponseEnum.OK, "Board updated successfully",
                mapper.convertToDTO(boardRepository.save(board.get())));
    }

    @Override
    public ResponsePayload<BoardDTO> getById(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        if (board.isPresent()) {
            return new ResponsePayload<>(ResponseEnum.OK, mapper.convertToDTO(board.get()));
        }
        return new ResponsePayload<>(ResponseEnum.NOTFOUND, MessageEnum.NOT_FOUND.getMessage());
    }

    @Override
    public ResponsePayload<List<BoardDTO>> getAllBoardsForUser(Long id) {
        List<Board> boards = boardRepository.findAllByMembers_Id(id);
        if (!boards.isEmpty()) {
            return new ResponsePayload<>(ResponseEnum.OK, mapper.mapList(boards));
        }
        return new ResponsePayload<>(ResponseEnum.BADREQUEST, MessageEnum.EMPTY_LIST.getMessage());
    }

    @Override
    public ResponsePayload<List<BoardDTO>> getAllBoardsByCreator(Long id) {
        List<Board> boards = boardRepository.findAllByCreator_Id(id);
        if (!boards.isEmpty()) {
            return new ResponsePayload<>(ResponseEnum.OK, mapper.mapList(boards));
        }
        return new ResponsePayload<>(ResponseEnum.BADREQUEST, MessageEnum.EMPTY_LIST.getMessage());
    }
}
