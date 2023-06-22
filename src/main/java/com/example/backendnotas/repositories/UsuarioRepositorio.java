package com.example.backendnotas.repositories;

import com.example.backendnotas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    public Usuario findByUsername(String username);

}
