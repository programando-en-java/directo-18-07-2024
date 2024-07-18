package com.programandoenjava.ejemplo_proyecto.services;

import com.programandoenjava.ejemplo_proyecto.dto.CreateUserDTO;
import com.programandoenjava.ejemplo_proyecto.dto.UserDTO;
import com.programandoenjava.ejemplo_proyecto.models.User;
import com.programandoenjava.ejemplo_proyecto.repositories.UserRepository;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceV2Impl implements UserService {

    private final UserRepository userRepository;

    public UserServiceV2Impl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Page<UserDTO> getUsers(@Nullable final Pageable pageable) {
        Page<User> queryResult;
        if (pageable == null) {
            queryResult = userRepository.findAll(Pageable.unpaged());
        } else {
            queryResult = userRepository.findAll(pageable);
        }

        final List<UserDTO> allUsersDTO = queryResult.stream()
                .map(user -> new UserDTO(user.getId(), user.getEmail()))
                .toList();
        return new PageImpl<>(allUsersDTO, queryResult.getPageable(), queryResult.getTotalElements());
    }

    @Override
    public UserDTO createUser(@NotNull final CreateUserDTO userDTO) {
        Objects.requireNonNull(userDTO, "El user no debe ser nulo");
        final var user = new User();
        user.setBirthday(userDTO.birthday());
        user.setEmail(userDTO.email());
        final User savedUser = userRepository.save(user);
        return new UserDTO(savedUser.getId(), savedUser.getEmail());
    }
}
