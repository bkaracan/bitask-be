package com.ilkayburak.bitask.service.impl;

import com.ilkayburak.bitask.dto.UserDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;
import com.ilkayburak.bitask.entity.User;
import com.ilkayburak.bitask.entity.UserStatus;
import com.ilkayburak.bitask.enumarations.UserStatusEnum;
import com.ilkayburak.bitask.enumarations.core.MessageEnum;
import com.ilkayburak.bitask.enumarations.core.ResponseEnum;
import com.ilkayburak.bitask.mapper.UserDTOMapper;
import com.ilkayburak.bitask.repository.UserRepository;
import com.ilkayburak.bitask.repository.UserStatusRepository;
import com.ilkayburak.bitask.service.UserService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

  private final UserRepository repository;
  private final UserDTOMapper mapper;
  private final UserStatusRepository userStatusRepository;

  @Override
  public ResponsePayload<UserDTO> getById(Long id) {
    Optional<User> user = repository.findById(id);
    if (user.isPresent()) {
      return new ResponsePayload<>(ResponseEnum.OK, mapper.convertToDto(user.get()));
    }
    return new ResponsePayload<>(ResponseEnum.NOTFOUND, MessageEnum.NOT_FOUND.getMessage());
  }

  @Override
  public ResponsePayload<UserDTO> getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return new ResponsePayload<>(
        ResponseEnum.OK, mapper.convertToDto((User) authentication.getPrincipal()));
  }

  @Override
  public ResponsePayload<UserDTO> getByEmail(String email) {
    Optional<User> user = repository.findByEmail(email);
    if (user.isPresent()) {
      return new ResponsePayload<>(ResponseEnum.OK, mapper.convertToDto(user.get()));
    }
    return new ResponsePayload<>(ResponseEnum.NOTFOUND, MessageEnum.NOT_FOUND.getMessage());
  }

  @Override
  public ResponsePayload<List<UserDTO>> getAllUsers() {
    return new ResponsePayload<>(ResponseEnum.OK, mapper.mapList(repository.findAll()));
  }

  @Override
  public ResponsePayload<String> updateUserStatus(String statusName) {
    // Mevcut oturumdaki kullanıcıyı alıyoruz
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User currentUser = (User) authentication.getPrincipal();

    // UserStatus entity'sini isme göre buluyoruz
    Optional<UserStatus> userStatusOpt = userStatusRepository.findByName(statusName);

    if (userStatusOpt.isEmpty()) {
      return new ResponsePayload<>(ResponseEnum.ERROR, MessageEnum.NOT_FOUND.getMessage());
    }

    // Kullanıcının durumunu (status) güncelliyoruz
    UserStatus userStatus = userStatusOpt.get();
    currentUser.setUserStatus(userStatus);

    // Değişikliği veritabanına kaydediyoruz
    repository.save(currentUser);

    // Başarılı bir yanıt döndürüyoruz
    return new ResponsePayload<>(
        ResponseEnum.OK, MessageEnum.UPDATE_SUCCESS.getMessage(), true, userStatus.getName());
  }
}
