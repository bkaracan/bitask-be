package com.ilkayburak.bitask.service.impl;

import com.ilkayburak.bitask.dto.TaskDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;
import com.ilkayburak.bitask.entity.Task;
import com.ilkayburak.bitask.enumarations.core.MessageEnum;
import com.ilkayburak.bitask.enumarations.core.ResponseEnum;
import com.ilkayburak.bitask.mapper.TaskDTOMapper;
import com.ilkayburak.bitask.repository.TaskRepository;
import com.ilkayburak.bitask.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final TaskDTOMapper mapper;

    @Override
    public ResponsePayload<TaskDTO> save(TaskDTO taskDTO) {
        Optional<Task> task = repository.findById(taskDTO.getId());
        if (task.isEmpty()) {
            return new ResponsePayload<>(
                    ResponseEnum.OK,
                    "Task created successfully.",
                    mapper.convertToDto(repository.save(mapper.convertToEntity(taskDTO))));
        }
        return new ResponsePayload<>(
                ResponseEnum.OK,
                "Task updated successfully.",
                mapper.convertToDto(repository.save(mapper.convertToEntity(taskDTO))));
    }

    @Override
    public ResponsePayload<TaskDTO> getById(Long id) {
        Optional<Task> task = repository.findById(id);
        if (task.isPresent()) {
            return new ResponsePayload<>(ResponseEnum.OK, mapper.convertToDto(task.get()));
        }
        return new ResponsePayload<>(ResponseEnum.NOTFOUND, MessageEnum.NOT_FOUND.getMessage());
    }

    @Override
    public ResponsePayload<TaskDTO> getByKeyIdAndBoardId(String keyId, Long boardId) {
        Optional<Task> task = repository.findByKeyIdAndBoardId(keyId, boardId);
        if (task.isPresent()) {
            return new ResponsePayload<>(ResponseEnum.OK, mapper.convertToDto(task.get()));
        }
        return new ResponsePayload<>(ResponseEnum.NOTFOUND, MessageEnum.NOT_FOUND.getMessage());
    }

    @Override
    public ResponsePayload<List<TaskDTO>> getAllTaskByBoardId(Long boardId) {
        List<Task> tasks = repository.findAllByBoardId(boardId);
        if (!tasks.isEmpty()) {
            return new ResponsePayload<>(ResponseEnum.OK, mapper.mapList(tasks));
        }
        return new ResponsePayload<>(ResponseEnum.BADREQUEST, MessageEnum.EMPTY_LIST.getMessage());
    }

    @Override
    public ResponsePayload<List<TaskDTO>> getAllTaskByStatusAndBoardId(Long status, Long boardId) {
        List<Task> tasks = repository.findAllByBoardIdAndStatus(boardId, status);
        if (!tasks.isEmpty()) {
            return new ResponsePayload<>(ResponseEnum.OK, mapper.mapList(tasks));
        }
        return new ResponsePayload<>(ResponseEnum.BADREQUEST, MessageEnum.EMPTY_LIST.getMessage());
    }

    @Override
    public ResponsePayload<List<TaskDTO>> getAllTaskByStatusAndUserId(Long status, Long userId) {
        List<Task> tasks = repository.findAllByStatusAndAssignees_Id(status, userId);
        if (!tasks.isEmpty()) {
            return new ResponsePayload<>(ResponseEnum.OK, mapper.mapList(tasks));
        }
        return new ResponsePayload<>(ResponseEnum.BADREQUEST, MessageEnum.EMPTY_LIST.getMessage());
    }
}
