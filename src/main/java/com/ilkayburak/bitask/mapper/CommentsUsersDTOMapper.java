package com.ilkayburak.bitask.mapper;

import com.ilkayburak.bitask.dto.CommentsUsersDTO;
import com.ilkayburak.bitask.entity.CommentsUsers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentsUsersDTOMapper {

    public CommentsUsersDTO convertToDto(CommentsUsers commentsUsers) {
        if (commentsUsers == null) {
            return null;
        }
        return CommentsUsersDTO.builder()
                .id(commentsUsers.getId())
                .comment(new CommentsDTOMapper().convertToDto(commentsUsers.getComment()))
                .user(new UserDTOMapper().convertToDto(commentsUsers.getTaggedUser()))
                .build();
    }

    public CommentsUsers convertToEntity(CommentsUsersDTO commentsUsersDTO) {
        if (commentsUsersDTO == null) {
            return null;
        }
        return CommentsUsers.builder()
                .id(commentsUsersDTO.getId())
                .comment(new CommentsDTOMapper().convertToEntity(commentsUsersDTO.getComment()))
                .taggedUser(new UserDTOMapper().convertToEntity(commentsUsersDTO.getUser()))
                .build();
    }

    public List<CommentsUsersDTO> mapList(List<CommentsUsers> list) {
        return list.stream().map(this::convertToDto).toList();
    }

    public List<CommentsUsers> mapListToEntity(List<CommentsUsersDTO> list) {
        return list.stream().map(this::convertToEntity).toList();
    }

}
