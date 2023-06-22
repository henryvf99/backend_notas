package com.example.backendnotas.repositories;

import com.example.backendnotas.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepositorio extends JpaRepository<Nota, Long> {
}
