package com.ilkayburak.bitask.mapper;

import com.ilkayburak.bitask.dto.CommentsUsersDTO;
import com.ilkayburak.bitask.entity.CommentsUsers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentsUsersDTOMapper {

    public CommentsUsersDTO convertToDTO(CommentsUsers commentsUsers) {
        if (commentsUsers == null) {
            return null;
        }
        return CommentsUsersDTO.builder()
                .id(commentsUsers.getId())
                .build();
    }

    public CommentsUsers convertToEntity(CommentsUsersDTO commentsUsersDTO) {
        if (commentsUsersDTO == null) {
            return null;
        }
        return CommentsUsers.builder()
                .id(commentsUsersDTO.getId())
                .build();
    }

    public List<CommentsUsersDTO> mapList(List<CommentsUsers> list) {
        return list.stream().map(this::convertToDTO).toList();
    }

    public List<CommentsUsers> convertListToEntity(List<CommentsUsersDTO> list) {
        return list.stream().map(this::convertToEntity).toList();
    }

    public CommentsUsersDTO mapWithObjects(CommentsUsers commentsUsers) {
        if (commentsUsers == null) {
            return null;
        }
        return CommentsUsersDTO.builder()
                .id(commentsUsers.getId())
                .comment(new CommentsDTOMapper().convertToDto(commentsUsers.getComment()))
                .user(new UserDTOMapper().mapWithoutObjects(commentsUsers.getTaggedUser()))
                .build();
    }

    public List<CommentsUsersDTO> mapListWithObjects(List<CommentsUsers> list) {
        return list.stream().map(this::mapWithObjects).toList();
    }

}
