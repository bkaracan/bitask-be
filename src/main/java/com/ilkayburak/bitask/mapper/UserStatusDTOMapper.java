package com.ilkayburak.bitask.mapper;

import com.ilkayburak.bitask.dto.UserStatusDTO;
import com.ilkayburak.bitask.entity.UserStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserStatusDTOMapper {

    public UserStatusDTO convertToDto(UserStatus userStatus) {
        if (userStatus == null) {
            return null;
        }
        return UserStatusDTO.builder()
                .id(userStatus.getId())
                .name(userStatus.getName())
                .users(new UserDTOMapper().mapList(userStatus.getUsers()))
                .build();
    }

    public UserStatus convertToEntity(UserStatusDTO userStatusDTO) {
        if (userStatusDTO == null) {
            return null;
        }
        return UserStatus.builder()
                .id(userStatusDTO.getId())
                .name(userStatusDTO.getName())
                .users(new UserDTOMapper().convertListToEntity(userStatusDTO.getUsers()))
                .build();
    }

    public List<UserStatusDTO> mapList(List<UserStatus> list) {
        return list.stream().map(this::convertToDto).toList();
    }

    public List<UserStatus> mapListToEntity(List<UserStatusDTO> list) {
        return list.stream().map(this::convertToEntity).toList();
    }

    public UserStatusDTO mapWithoutObject(UserStatus userStatus) {
        if (userStatus == null) {
            return null;
        }
        return UserStatusDTO.builder()
                .id(userStatus.getId())
                .name(userStatus.getName())
                .build();
    }

    public List<UserStatusDTO> mapListWithoutObject(List<UserStatus> list) {
        return list.stream().map(this::mapWithoutObject).toList();
    }
}
