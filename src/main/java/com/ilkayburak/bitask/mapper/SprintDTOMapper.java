package com.ilkayburak.bitask.mapper;

import com.ilkayburak.bitask.dto.SprintDTO;
import com.ilkayburak.bitask.entity.Sprint;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SprintDTOMapper {

    public SprintDTO convertToDto(Sprint sprint) {
        if (sprint == null) {
            return null;
        }
        return SprintDTO.builder()
                .id(sprint.getId())
                .name(sprint.getName())
                .createdAt(sprint.getCreatedAt())
                .startDate(sprint.getStartDate())
                .endDate(sprint.getEndDate())
                .status(sprint.getStatus())
                .task(new TaskDTOMapper().mapList(sprint.getTasks()))
                .board(new BoardDTOMapper().convertToDTO(sprint.getBoard()))
                .build();
    }

    public Sprint convertToEntity(SprintDTO sprintDTO) {
        if (sprintDTO == null) {
            return null;
        }
        return Sprint.builder()
                .id(sprintDTO.getId())
                .name(sprintDTO.getName())
                .createdAt(sprintDTO.getCreatedAt())
                .startDate(sprintDTO.getStartDate())
                .endDate(sprintDTO.getEndDate())
                .status(sprintDTO.getStatus())
                .tasks(new TaskDTOMapper().convertListToEntity(sprintDTO.getTask()))
                .board(new BoardDTOMapper().convertToEntity(sprintDTO.getBoard()))
                .build();
    }

    public List<SprintDTO> mapList(List<Sprint> list) {
        return list.stream().map(this::convertToDto).toList();
    }

    public List<Sprint> convertListToEntity(List<SprintDTO> list) {
        return list.stream().map(this::convertToEntity).toList();
    }

    public SprintDTO mapWithoutObject(Sprint sprint) {
        if (sprint == null) {
            return null;
        }
        return SprintDTO.builder()
                .id(sprint.getId())
                .name(sprint.getName())
                .createdAt(sprint.getCreatedAt())
                .startDate(sprint.getStartDate())
                .endDate(sprint.getEndDate())
                .status(sprint.getStatus())
                .build();
    }

    public List<SprintDTO> mapListWithoutObject(List<Sprint> list) {
        return list.stream().map(this::mapWithoutObject).toList();
    }
}
