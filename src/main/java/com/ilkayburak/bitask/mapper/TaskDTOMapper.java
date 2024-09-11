package com.ilkayburak.bitask.mapper;

import com.ilkayburak.bitask.dto.TaskDTO;
import com.ilkayburak.bitask.entity.Task;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskDTOMapper {

    public TaskDTO convertToDto(Task task) {
        if (task == null) {
            return null;
        }
        return TaskDTO.builder()
                .id(task.getId())
                .keyId(task.getKeyId())
                .title(task.getTitle())
                .description(task.getDescription())
                .priority(task.getPriority())
                .status(task.getStatus())
                .createdDate(task.getCreatedDate())
                .expectedFinishDate(task.getExpectedFinishDate())
                .deadline(task.getDeadline())
                .board(new BoardDTOMapper().convertToDTO(task.getBoard()))
                .assignees(new UserDTOMapper().mapList(task.getAssignees()))
                .blockingTask(task.getBlockingTask())
                .sprint(new SprintDTOMapper().convertToDto(task.getSprint()))
                .build();
    }

    public Task convertToEntity(TaskDTO taskDTO) {
        return Task.builder()
                .id(taskDTO.getId())
                .keyId(taskDTO.getKeyId())
                .title(taskDTO.getTitle())
                .description(taskDTO.getDescription())
                .priority(taskDTO.getPriority())
                .status(taskDTO.getStatus())
                .createdDate(taskDTO.getCreatedDate())
                .expectedFinishDate(taskDTO.getExpectedFinishDate())
                .deadline(taskDTO.getDeadline())
                .board(new BoardDTOMapper().convertToEntity(taskDTO.getBoard()))
                .assignees(new UserDTOMapper().convertListToEntity(taskDTO.getAssignees()))
                .blockingTask(taskDTO.getBlockingTask())
                .sprint(new SprintDTOMapper().convertToEntity(taskDTO.getSprint()))
                .build();
    }

    public List<TaskDTO> mapList(List<Task> list) {
        return list.stream().map(this::convertToDto).toList();
    }

    public List<Task> convertListToEntity(List<TaskDTO> list) {
        return list.stream().map(this::convertToEntity).toList();
    }

    public TaskDTO mapWithoutObjects(Task task) {
        if (task == null) {
            return null;
        }
        return TaskDTO.builder()
                .id(task.getId())
                .keyId(task.getKeyId())
                .title(task.getTitle())
                .description(task.getDescription())
                .priority(task.getPriority())
                .status(task.getStatus())
                .createdDate(task.getCreatedDate())
                .expectedFinishDate(task.getExpectedFinishDate())
                .deadline(task.getDeadline())
                .blockingTask(task.getBlockingTask())
                .build();
    }

    public List<TaskDTO> mapListWithoutObjects(List<Task> list) {
        return list.stream().map(this::mapWithoutObjects).toList();
    }
}
