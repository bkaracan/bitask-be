package com.ilkayburak.bitask.mapper;

import com.ilkayburak.bitask.dto.RoleDTO;
import com.ilkayburak.bitask.entity.Role;
import com.ilkayburak.bitask.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleDTOMapper {

  public RoleDTO convertToDto(Role role) {
    if (role == null) {
      return null;
    }
    return RoleDTO.builder()
        .id(role.getId())
        .name(role.getName())
        .userIds(role.getUsers().stream().map(User::getId).collect(Collectors.toList()))  // User Id'leri listeler
        .createdDate(role.getCreatedDate())
        .lastModifiedDate(role.getLastModifiedDate())
        .build();
  }

  public Role convertToEntity(RoleDTO roleDTO) {
    if (roleDTO == null) {
      return null;
    }
    // User Id'lerden User entity'leri oluşturulmadığı için burada user listesini null bırakıyoruz.
    return Role.builder()
        .id(roleDTO.getId())
        .name(roleDTO.getName())
        .users(null)  // Bu alan daha sonra gerekli User entity'leriyle doldurulabilir.
        .createdDate(roleDTO.getCreatedDate())
        .lastModifiedDate(roleDTO.getLastModifiedDate())
        .build();
  }

  public List<RoleDTO> mapList(List<Role> roles) {
    return roles.stream()
        .map(this::convertToDto)
        .collect(Collectors.toList());
  }

  public List<Role> convertListToEntity(List<RoleDTO> roleDTOs) {
    return roleDTOs.stream()
        .map(this::convertToEntity)
        .collect(Collectors.toList());
  }

  public RoleDTO mapWithoutObjects(Role role) {
    if (role == null) {
      return null;
    }
    return RoleDTO.builder()
        .id(role.getId())
        .name(role.getName())
        .createdDate(role.getCreatedDate())
        .lastModifiedDate(role.getLastModifiedDate())
        .build();
  }

  public RoleDTO mapWithObjects(Role role) {
    if (role == null) {
      return null;
    }
    return RoleDTO.builder()
        .id(role.getId())
        .name(role.getName())
        .userIds(role.getUsers().stream().map(User::getId).collect(Collectors.toList()))  // User Id'leri listeler
        .createdDate(role.getCreatedDate())
        .lastModifiedDate(role.getLastModifiedDate())
        .build();
  }

  public List<RoleDTO> mapListWithoutObjects(List<Role> roles) {
    return roles.stream()
        .map(this::mapWithoutObjects)
        .collect(Collectors.toList());
  }

  public List<RoleDTO> mapListWithObjects(List<Role> roles) {
    return roles.stream()
        .map(this::mapWithObjects)
        .collect(Collectors.toList());
  }
}
