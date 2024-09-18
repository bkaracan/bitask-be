package com.ilkayburak.bitask.controller;

import com.ilkayburak.bitask.dto.UserDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;
import com.ilkayburak.bitask.entity.User;
import com.ilkayburak.bitask.enumarations.core.ResponseEnum;
import com.ilkayburak.bitask.mapper.UserDTOMapper;
import com.ilkayburak.bitask.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;
    private final UserDTOMapper mapper;

    @GetMapping("/getCurrentUser")
    @Operation(summary = "Return the current user")
    public ResponsePayload<UserDTO> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new ResponsePayload<>(ResponseEnum.OK, mapper.convertToDto((User) authentication.getPrincipal()));
    }

    @GetMapping("/getUserById")
    @Operation(summary = "Get user by ID")
    public ResponsePayload<UserDTO> getUserById(@RequestParam("id") Long id) {
        return userService.getById(id);
    }

    @GetMapping("/getByEmail")
    @Operation(summary = "Get user by email address")
    public ResponsePayload<UserDTO> getByEmail(@RequestParam("email") String email) {
        return userService.getByEmail(email);
    }

    @GetMapping("/getAllUsers")
    @Operation(summary = "Get all users")
    public ResponsePayload<List<UserDTO>> getAllUsers() {
        return userService.getAllUsers();
    }

}
