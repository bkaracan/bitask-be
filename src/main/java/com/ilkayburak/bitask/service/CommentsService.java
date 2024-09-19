package com.ilkayburak.bitask.service;

import com.ilkayburak.bitask.dto.CommentsDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;

import java.util.List;

public interface CommentsService {

    ResponsePayload<CommentsDTO> save(CommentsDTO commentsDTO);
    ResponsePayload<CommentsDTO> getById(Long id);
    ResponsePayload<List<CommentsDTO>> getAllCommentsByTaskId(Long taskId);
    ResponsePayload<Void> deleteById(Long id);

}
