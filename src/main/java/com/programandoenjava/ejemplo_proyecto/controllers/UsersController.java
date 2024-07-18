package com.programandoenjava.ejemplo_proyecto.controllers;

import com.programandoenjava.ejemplo_proyecto.dto.CreateUserDTO;
import com.programandoenjava.ejemplo_proyecto.dto.UserDTO;
import com.programandoenjava.ejemplo_proyecto.dto.UserRequest;
import com.programandoenjava.ejemplo_proyecto.dto.UserResponse;
import com.programandoenjava.ejemplo_proyecto.services.UserService;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponse postUser(@RequestBody @Validated UserRequest userRequest) {
        final var userCreate = new CreateUserDTO(userRequest.email(), userRequest.birthday());
        final UserDTO result = userService.createUser(userCreate);
        return new UserResponse(result.email());
    }

    @GetMapping
    public Page<UserResponse> getUsers(@Nullable @PageableDefault Pageable pageable) {
        final Page<UserDTO> result = userService.getUsers(pageable);
        final List<UserResponse> users = result.map(userDTO -> new UserResponse(userDTO.email())).toList();
        return new PageImpl<>(users, result.getPageable(), result.getTotalElements());
    }
}
