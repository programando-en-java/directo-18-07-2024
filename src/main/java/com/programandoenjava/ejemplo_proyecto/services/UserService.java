package com.programandoenjava.ejemplo_proyecto.services;

import com.programandoenjava.ejemplo_proyecto.dto.CreateUserDTO;
import com.programandoenjava.ejemplo_proyecto.dto.UserDTO;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserDTO> getUsers(@Nullable Pageable pageable);

    UserDTO createUser(@NotNull CreateUserDTO userDTO);
}
