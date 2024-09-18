package com.ilkayburak.bitask.service.impl;

import com.ilkayburak.bitask.dto.UserDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;
import com.ilkayburak.bitask.entity.User;
import com.ilkayburak.bitask.enumarations.core.MessageEnum;
import com.ilkayburak.bitask.enumarations.core.ResponseEnum;
import com.ilkayburak.bitask.mapper.UserDTOMapper;
import com.ilkayburak.bitask.repository.UserRepository;
import com.ilkayburak.bitask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserDTOMapper mapper;

    @Override
    public ResponsePayload<UserDTO> getById(Long id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            return new ResponsePayload<>(ResponseEnum.OK, mapper.convertToDto(user.get()));
        }
        return new ResponsePayload<>(ResponseEnum.NOTFOUND, MessageEnum.NOT_FOUND.getMessage());
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
}
