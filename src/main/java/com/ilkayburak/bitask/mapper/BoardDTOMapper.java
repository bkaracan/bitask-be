package com.ilkayburak.bitask.mapper;

import com.ilkayburak.bitask.dto.BoardDTO;
import com.ilkayburak.bitask.dto.CreateBoardRequestDTO;
import com.ilkayburak.bitask.dto.UpdateBoardRequestDTO;
import com.ilkayburak.bitask.entity.Board;
import com.ilkayburak.bitask.entity.User;
import java.time.LocalDateTime;
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
        .members(board.getMembers().stream().map(new UserDTOMapper()::convertToDto).toList())
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

  public Board convertToEntityForScreenDTO(
      CreateBoardRequestDTO createBoardRequestDTO, User creator) {
    if (createBoardRequestDTO == null || creator == null) {
      return null;
    }
    return Board.builder()
        .name(createBoardRequestDTO.getName())
        .createDate(LocalDateTime.now())
        .creator(creator)
        .build();
  }

  public BoardDTO mapForUpdateBoardRequest(
      Board board, UpdateBoardRequestDTO updateBoardRequestDTO) {
    if (board == null || updateBoardRequestDTO == null) {
      return null;
    }
    return BoardDTO.builder()
        .id(board.getId())
        .name(
            updateBoardRequestDTO.getName() != null
                ? updateBoardRequestDTO.getName()
                : board.getName())
        .createDate(board.getCreateDate())
        .creator(new UserDTOMapper().convertToDto(board.getCreator()))
        .members(
            updateBoardRequestDTO.getMembersToAdd() != null
                    || updateBoardRequestDTO.getMembersToRemove() != null
                ? new UserDTOMapper().mapListWithoutObjects(board.getMembers())
                : null)
        .tasks(new TaskDTOMapper().mapList(board.getTasks()))
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
