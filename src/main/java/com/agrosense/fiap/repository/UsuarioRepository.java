package com.agrosense.fiap.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrosense.fiap.model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
    Optional<UsuarioModel> findByNmEmail(String email);
}
