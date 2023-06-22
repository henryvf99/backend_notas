package com.example.backendnotas.controllers;

import com.example.backendnotas.model.Nota;
import com.example.backendnotas.services.NotaService;
import com.example.backendnotas.utils.WrapperResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
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

    /*@PostMapping
    public ResponseEntity<WrapperResponse<Nota>> agregarNota(@RequestBody Nota nota){
        Nota notaNew = notaService.agregarNota(nota);
        return new WrapperResponse<Nota>(true, "success", notaNew)
                .createResponse(HttpStatus.OK);
    }*/

    @PostMapping
    public ResponseEntity<WrapperResponse<Nota>> agregarNota(@RequestBody Nota nota, @RequestHeader("Authorization") String authorizationHeader){

        String token = authorizationHeader.substring(7);

        Claims claims = Jwts.parser()
                .setSigningKey("614E645267556B58703273357638782F413F4428472B4B6250655368566D597133743677397A244226452948404D635166546A576E5A7234753778214125442A")
                .parseClaimsJws(token)
                .getBody();

        String userId = claims.getSubject();

        nota.setUsuario(userId);
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
