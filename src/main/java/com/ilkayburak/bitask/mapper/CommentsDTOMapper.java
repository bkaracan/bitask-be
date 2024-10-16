package com.ilkayburak.bitask.mapper;

import com.ilkayburak.bitask.dto.CommentsDTO;
import com.ilkayburak.bitask.entity.Comments;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentsDTOMapper {

    public CommentsDTO convertToDto(Comments comments) {
        if (comments == null) {
            return null;
        }
        return CommentsDTO.builder()
                .id(comments.getId())
                .taskId(comments.getTaskId())
                .boardId(comments.getBoardId())
                .comment(comments.getComment())
                .createdAt(comments.getCreatedAt())
                .updatedAt(comments.getUpdatedAt())
                .build();
    }

    public Comments convertToEntity(CommentsDTO commentsDTO) {
        if (commentsDTO == null) {
            return null;
        }
        return Comments.builder()
                .id(commentsDTO.getId())
                .taskId(commentsDTO.getTaskId())
                .boardId(commentsDTO.getBoardId())
                .comment(commentsDTO.getComment())
                .createdAt(commentsDTO.getCreatedAt())
                .updatedAt(commentsDTO.getUpdatedAt())
                .build();
    }

    public List<CommentsDTO> mapList(List<Comments> list) {
        return list.stream().map(this::convertToDto).toList();
    }

    public List<Comments> convertListToEntity(List<CommentsDTO> list) {
        return list.stream().map(this::convertToEntity).toList();
    }

    public CommentsDTO mapWithObjects(Comments comments) {
        if (comments == null) {
            return null;
        }
        return CommentsDTO.builder()
                .id(comments.getId())
                .taskId(comments.getTaskId())
                .boardId(comments.getBoardId())
                .user(new UserDTOMapper().mapWithoutObjects(comments.getUser()))
                .comment(comments.getComment())
                .createdAt(comments.getCreatedAt())
                .updatedAt(comments.getUpdatedAt())
                .build();
    }

    public List<CommentsDTO> mapListWithObjects(List<Comments> list) {
        return list.stream().map(this::mapWithObjects).toList();
    }

}
