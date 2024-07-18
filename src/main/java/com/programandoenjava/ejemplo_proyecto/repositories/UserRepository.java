package com.programandoenjava.ejemplo_proyecto.repositories;

import com.programandoenjava.ejemplo_proyecto.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
