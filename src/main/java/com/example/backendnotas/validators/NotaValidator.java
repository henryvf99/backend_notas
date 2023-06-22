package com.example.backendnotas.validators;

import com.example.backendnotas.exceptions.IncorrectResourceRequestException;
import com.example.backendnotas.model.Nota;

public class NotaValidator {

    public static void validate(Nota nota){

        if(nota.getTitulo() == null || nota.getTitulo().trim().isEmpty()) {
            throw new IncorrectResourceRequestException("El titulo es requerido");
        }

        if(nota.getTitulo().length() < 3) {
            throw new IncorrectResourceRequestException("El titulo debe ser mayor a 3 caracteres");
        }

        if(nota.getDescripcion() == null || nota.getDescripcion().trim().isEmpty()) {
            throw new IncorrectResourceRequestException("La descripción es requerido");
        }

        if(nota.getDescripcion().length() < 3) {
            throw new IncorrectResourceRequestException("La descripción debe ser mayor a 3 caracteres");
        }

    }

}
