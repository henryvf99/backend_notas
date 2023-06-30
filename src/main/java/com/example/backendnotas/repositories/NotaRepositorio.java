package com.example.backendnotas.repositories;

import com.example.backendnotas.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaRepositorio extends JpaRepository<Nota, Long> {

    List<Nota> findByIdusuario(Integer idusuario);

}
