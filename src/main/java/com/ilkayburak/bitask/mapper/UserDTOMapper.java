package com.ilkayburak.bitask.mapper;

import com.ilkayburak.bitask.dto.RegistrationRequestDTO;
import com.ilkayburak.bitask.dto.UserDTO;
import com.ilkayburak.bitask.entity.JobTitle;
import com.ilkayburak.bitask.entity.User;
import com.ilkayburak.bitask.entity.Role;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserDTOMapper {

  public UserDTO convertToDto(User user) {
    if (user == null) {
      return null;
    }
    return UserDTO.builder()
        .id(user.getId())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .dateOfBirth(user.getDateOfBirth())
        .email(user.getEmail())
        .jobTitle(new JobTitleDTOMapper().convertToDto(user.getJobTitle()))
        .isAccountLocked(user.isAccountLocked())
        .isEnabled(user.isEnabled())
        .createdDate(user.getCreatedDate())
        .lastModifiedDate(user.getLastModifiedDate())
        .roles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
        .build();
  }

  public User convertToEntity(UserDTO userDTO) {
    if (userDTO == null) {
      return null;
    }
    return User.builder()
        .id(userDTO.getId())
        .firstName(userDTO.getFirstName())
        .lastName(userDTO.getLastName())
        .dateOfBirth(userDTO.getDateOfBirth())
        .email(userDTO.getEmail())
        .jobTitle(new JobTitleDTOMapper().convertToEntity(userDTO.getJobTitle()))
        .isAccountLocked(userDTO.isAccountLocked())
        .isEnabled(userDTO.isEnabled())
        .build();
  }

  public List<UserDTO> mapList(List<User> users) {
    return users.stream()
        .map(this::convertToDto)
        .collect(Collectors.toList());
  }

  public List<User> convertListToEntity(List<UserDTO> userDTOs) {
    return userDTOs.stream()
        .map(this::convertToEntity)
        .collect(Collectors.toList());
  }

  public UserDTO mapWithoutObjects(User user) {
    if (user == null) {
      return null;
    }
    return UserDTO.builder()
        .id(user.getId())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .dateOfBirth(user.getDateOfBirth())
        .email(user.getEmail())
        .isAccountLocked(user.isAccountLocked())
        .isEnabled(user.isEnabled())
        .createdDate(user.getCreatedDate())
        .lastModifiedDate(user.getLastModifiedDate())
        .build();
  }

  public UserDTO mapWithObjects(User user) {
    if (user == null) {
      return null;
    }
    return UserDTO.builder()
        .id(user.getId())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .dateOfBirth(user.getDateOfBirth())
        .email(user.getEmail())
        .jobTitle(new JobTitleDTOMapper().mapWithoutObjects(user.getJobTitle()))
        .isAccountLocked(user.isAccountLocked())
        .isEnabled(user.isEnabled())
        .createdDate(user.getCreatedDate())
        .lastModifiedDate(user.getLastModifiedDate())
        .build();
  }

  public List<UserDTO> mapListWithoutObjects(List<User> users) {
    return users.stream()
        .map(this::mapWithoutObjects)
        .collect(Collectors.toList());
  }

  public List<UserDTO> mapListWithObjects(List<User> users) {
    return users.stream()
        .map(this::mapWithObjects)
        .collect(Collectors.toList());
  }

  public User mapForRegistration(RegistrationRequestDTO registrationRequestDTO, JobTitle jobTitle) {
    return User.builder()
        .firstName(registrationRequestDTO.getFormattedName(registrationRequestDTO.getFirstName()))
        .lastName(registrationRequestDTO.getFormattedName(registrationRequestDTO.getLastName()))
        .email(registrationRequestDTO.getEmail())
        .password(registrationRequestDTO.getPassword())  // Şifreyi encode ederken register metodu içinde encode edeceğiz
        .jobTitle(jobTitle)  // JobTitle entity'sini dışarıdan alıyoruz
        .dateOfBirth(registrationRequestDTO.getDateOfBirth())
        .isAccountLocked(false)
        .isEnabled(false)
        .build();
  }
}
