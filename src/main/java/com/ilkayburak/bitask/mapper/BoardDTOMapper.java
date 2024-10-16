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
                .creator(new UserDTOMapper().convertToDto(board.getCreator()))
                .members(new UserDTOMapper().mapList(board.getMembers()))
                .tasks(new TaskDTOMapper().mapList(board.getTasks()))
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
                .creator(new UserDTOMapper().convertToEntity(boardDTO.getCreator()))
                .members(new UserDTOMapper().convertListToEntity(boardDTO.getMembers()))
                .tasks(new TaskDTOMapper().convertListToEntity(boardDTO.getTasks()))
                .build();
    }

    public List<BoardDTO> mapList(List<Board> boards) {
        return boards.stream().map(this::convertToDTO).toList();
    }

    public List<Board> convertListToEntity(List<BoardDTO> list) {
        return list.stream().map(this::convertToEntity).toList();
    }

    public BoardDTO mapWithoutObjects(Board board) {
        if (board == null) {
            return null;
        }
        return BoardDTO.builder()
                .id(board.getId())
                .name(board.getName())
                .createDate(board.getCreateDate())
                .build();
    }

    public List<BoardDTO> mapListWithoutObjects(List<Board> boards) {
        return boards.stream().map(this::mapWithoutObjects).toList();
    }
}
