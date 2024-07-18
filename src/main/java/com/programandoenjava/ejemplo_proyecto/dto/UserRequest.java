package com.programandoenjava.ejemplo_proyecto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record UserRequest(
        @Email
        String email,
        @NotBlank
        String nombre,
        @Past
        LocalDate birthday
) {
}
