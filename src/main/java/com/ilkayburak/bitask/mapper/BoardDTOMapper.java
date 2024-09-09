package com.ilkayburak.bitask.mapper;

import com.ilkayburak.bitask.dto.BoardDTO;
import com.ilkayburak.bitask.entity.Board;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BoardDTOMapper {

    public BoardDTO convertToDTO(Board board) {
        if (board == null) {
            return null;
        }
        return BoardDTO.builder()
                .id(board.getId())
                .name(board.getName())
                .uniqueId(board.getUniqueId())
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
                .uniqueId(boardDTO.getUniqueId())
                .createDate(boardDTO.getCreateDate())
                .creator(new UserDTOMapper().convertToEntity(boardDTO.getCreator()))
                .members(new UserDTOMapper().convertListToEntity(boardDTO.getMembers()))
                .tasks(new TaskDTOMapper().convertListToEntity(boardDTO.getTasks()))
                .build();
    }

    public List<BoardDTO> mapList(List<Board> boards) {
        return boards.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<Board> convertListToEntity(List<BoardDTO> list) {
        return list.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    public BoardDTO mapWithoutObjects(Board board) {
        if (board == null) {
            return null;
        }
        return BoardDTO.builder()
                .id(board.getId())
                .name(board.getName())
                .uniqueId(board.getUniqueId())
                .createDate(board.getCreateDate())
                .build();
    }

    public List<BoardDTO> mapListWithoutObjects(List<Board> boards) {
        return boards.stream().map(this::mapWithoutObjects).collect(Collectors.toList());
    }
}
