package com.programandoenjava.ejemplo_proyecto.dto;

import java.time.LocalDate;

public record CreateUserDTO(
        String email,
        LocalDate birthday
) {
}
