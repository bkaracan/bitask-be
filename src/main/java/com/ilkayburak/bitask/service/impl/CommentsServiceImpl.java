package com.ilkayburak.bitask.service.impl;

import com.ilkayburak.bitask.dto.CommentsDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;
import com.ilkayburak.bitask.entity.Comments;
import com.ilkayburak.bitask.enumarations.core.MessageEnum;
import com.ilkayburak.bitask.enumarations.core.ResponseEnum;
import com.ilkayburak.bitask.mapper.CommentsDTOMapper;
import com.ilkayburak.bitask.repository.CommentsRepository;
import com.ilkayburak.bitask.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentsServiceImpl implements CommentsService {

    private final CommentsRepository commentsRepository;
    private final CommentsDTOMapper mapper;

    @Override
    public ResponsePayload<CommentsDTO> save(CommentsDTO commentsDTO) {
        Optional<Comments> comments = commentsRepository.findById(commentsDTO.getId());
        if (comments.isEmpty()) {
            return new ResponsePayload<>(ResponseEnum.OK, "Comment posted successfully.",
                    mapper.convertToDto(commentsRepository.save(mapper.convertToEntity(commentsDTO))));
        }
        return new ResponsePayload<>(ResponseEnum.OK, "Comment updated successfully.",
                mapper.convertToDto(commentsRepository.save(mapper.convertToEntity(commentsDTO))));
    }

    @Override
    public ResponsePayload<CommentsDTO> getById(Long id) {
        Optional<Comments> comments = commentsRepository.findById(id);
        if (comments.isPresent()) {
            return new ResponsePayload<>(ResponseEnum.OK, mapper.convertToDto(comments.get()));
        }
        return new ResponsePayload<>(ResponseEnum.NOTFOUND, MessageEnum.NOT_FOUND.getMessage());
    }

    @Override
    public ResponsePayload<List<CommentsDTO>> getAllCommentsByTaskId(Long taskId) {
        List<Comments> comments = commentsRepository.findAllByTaskId(taskId);
        if (!comments.isEmpty()) {
            return new ResponsePayload<>(ResponseEnum.OK, mapper.mapList(comments));
        }
        return new ResponsePayload<>(ResponseEnum.NOTFOUND, MessageEnum.NOT_FOUND.getMessage());
    }

    @Override
    public ResponsePayload<Void> deleteById(Long id) {
        Optional<Comments> comments = commentsRepository.findById(id);
        if (comments.isPresent()) {
            commentsRepository.deleteById(id);
            return new ResponsePayload<>(ResponseEnum.OK, MessageEnum.DELETE_SUCCESS.getMessage());
        }
        return new ResponsePayload<>(ResponseEnum.NOTFOUND, MessageEnum.NOT_FOUND.getMessage());
    }
}
