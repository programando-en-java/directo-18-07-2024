package com.programandoenjava.ejemplo_proyecto.dto;

import java.util.UUID;

public record UserDTO(
        UUID id,
        String email
) {
}
