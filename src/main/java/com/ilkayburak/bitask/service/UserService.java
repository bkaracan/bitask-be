package com.ilkayburak.bitask.service;

import com.ilkayburak.bitask.dto.UserDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;

import java.util.List;

public interface UserService {

    ResponsePayload<UserDTO> getById(Long id);
    ResponsePayload<UserDTO> getCurrentUser();
    ResponsePayload<UserDTO> getByEmail(String email);
    ResponsePayload<List<UserDTO>> getAllUsers();

}
