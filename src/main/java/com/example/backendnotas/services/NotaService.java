package com.example.backendnotas.services;

import com.example.backendnotas.exceptions.GeneralServiceException;
import com.example.backendnotas.exceptions.IncorrectResourceRequestException;
import com.example.backendnotas.exceptions.ResourceNotFoundException;
import com.example.backendnotas.model.Nota;
import com.example.backendnotas.repositories.NotaRepositorio;
import com.example.backendnotas.validators.NotaValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    private final NotaRepositorio notaRepositorio;

    public NotaService(NotaRepositorio notaRepositorio) {
        this.notaRepositorio = notaRepositorio;
    }

    @Transactional
    public Nota agregarNota(Nota nota){
        try {
            NotaValidator.validate(nota);
            return notaRepositorio.save(nota);
        }catch (IncorrectResourceRequestException | ResourceNotFoundException e){
            throw e;
        }catch (Exception e){
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Transactional
    public Nota modificarNota(Nota nota){
        NotaValidator.validate(nota);

        Nota notaActualizada = notaRepositorio.findById(nota.getId()).orElseThrow(() -> new ResourceNotFoundException("no existe la nota"));

        notaActualizada.setTitulo(nota.getTitulo());
        notaActualizada.setDescripcion(nota.getDescripcion());

        return notaRepositorio.save(notaActualizada);

    }
    @Transactional(readOnly = true)
    public List<Nota> listarNotas(){
        return notaRepositorio.findAll();
    }

    @Transactional
    public List<Nota> listarNotasPorUsuario(Integer idusuario){
        return notaRepositorio.findByIdusuario(idusuario);
    }

    @Transactional
    public void eliminarNota(Long idNota){
        Nota nota = this.obtenerNotaPorId(idNota);
        notaRepositorio.delete(nota);
    }

    public Nota obtenerNotaPorId(Long id){
        Optional<Nota> nota = notaRepositorio.findById(id);
        return nota.orElseThrow(() -> new ResourceNotFoundException("Nota no found"));
    }

}
