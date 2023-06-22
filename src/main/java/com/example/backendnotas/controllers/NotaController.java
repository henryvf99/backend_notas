package com.example.backendnotas.controllers;

import com.example.backendnotas.model.Nota;
import com.example.backendnotas.services.NotaService;
import com.example.backendnotas.utils.WrapperResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notas")
public class NotaController {

    private final NotaService notaService;

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @PostMapping
    public ResponseEntity<WrapperResponse<Nota>> agregarNota(@RequestBody Nota nota){
        Nota notaNew = notaService.agregarNota(nota);
        return new WrapperResponse<Nota>(true, "success", notaNew)
                .createResponse(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<WrapperResponse<Nota>> modificarNota(@RequestBody Nota nota){
        Nota notaActualizada = notaService.modificarNota(nota);
        return new WrapperResponse<Nota>(true, "success", notaActualizada)
                .createResponse(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<WrapperResponse<List<Nota>>> ListarNotas(){
        List<Nota> notas = notaService.listarNotas();
        return new WrapperResponse<>(true, "success", notas).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WrapperResponse<Nota>> obtenerNotaPorId(@PathVariable("id") Long id){
        Nota nota = notaService.obtenerNotaPorId(id);
        return new WrapperResponse<>(true, "success", nota).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WrapperResponse<Void>> eliminarNota(@PathVariable Long id){
        notaService.eliminarNota(id);
        return new WrapperResponse<Void>(true, "success", null)
                .createResponse(HttpStatus.NO_CONTENT);
    }

}
