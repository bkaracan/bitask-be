package com.ilkayburak.bitask.mapper;

import com.ilkayburak.bitask.dto.BoardDTO;
import com.ilkayburak.bitask.entity.Board;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BoardDTOMapper {

    public BoardDTO convertToDTO(Board board) {
        if (board == null) {
            return null;
        }
        return BoardDTO.builder()
                .id(board.getId())
                .name(board.getName())
                .createDate(board.getCreateDate())
                .build();
    }

    public Board convertToEntity(BoardDTO boardDTO) {
        if (boardDTO == null) {
            return null;
        }
        return Board.builder()
                .id(boardDTO.getId())
                .name(boardDTO.getName())
                .createDate(boardDTO.getCreateDate())
                .build();
    }

    public List<BoardDTO> mapList(List<Board> list) {
        return list.stream().map(this::convertToDTO).toList();
    }

    public List<Board> convertListToEntity(List<BoardDTO> list) {
        return list.stream().map(this::convertToEntity).toList();
    }

    public BoardDTO mapWithObjects(Board board) {
        if (board == null) {
            return null;
        }
        return BoardDTO.builder()
                .id(board.getId())
                .name(board.getName())
                .createDate(board.getCreateDate())
                .creator(new UserDTOMapper().convertToDto(board.getCreator()))
                .members(new UserDTOMapper().mapListWithoutObjects(board.getMembers()))
                .tasks(new TaskDTOMapper().mapList(board.getTasks()))
                .build();
    }

    public List<BoardDTO> mapListWithObjects(List<Board> list) {
        return list.stream().map(this::mapWithObjects).toList();
    }
}
