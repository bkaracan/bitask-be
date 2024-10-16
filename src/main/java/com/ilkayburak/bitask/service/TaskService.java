package com.ilkayburak.bitask.service;

import com.ilkayburak.bitask.dto.TaskDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;

import java.util.List;

public interface TaskService {

    ResponsePayload<TaskDTO> save(TaskDTO taskDTO);
    ResponsePayload<TaskDTO> update(TaskDTO taskDTO);
    ResponsePayload<TaskDTO> getById(Long id);
    ResponsePayload<TaskDTO> getByKeyIdAndBoardId(String keyId, Long boardId);
    ResponsePayload<List<TaskDTO>> getAllTaskByBoardId(Long boardId);
    ResponsePayload<List<TaskDTO>> getAllTaskByStatusAndBoardId(Long status, Long boardId);
    ResponsePayload<List<TaskDTO>> getAllTaskByStatusAndUserId(Long status, Long userId);
    ResponsePayload<Void> deleteById(Long id);

}
