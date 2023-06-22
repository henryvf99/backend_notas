package com.example.backendnotas.services.impl;

import com.example.backendnotas.exceptions.UsuarioFoundException;
import com.example.backendnotas.model.Usuario;
import com.example.backendnotas.model.UsuarioRol;
import com.example.backendnotas.repositories.RolRepositorio;
import com.example.backendnotas.repositories.UsuarioRepositorio;
import com.example.backendnotas.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private RolRepositorio rolRepositorio;

    @Override
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuariosRoles) throws Exception {
        Usuario usuarioLocal = usuarioRepositorio.findByUsername(usuario.getUsername());
        if (usuarioLocal != null){
            System.out.println("El usuario ya existe");
            throw new UsuarioFoundException("El usuario ya esta presente");
        }else{
            for (UsuarioRol usuarioRol:usuariosRoles){
                rolRepositorio.save(usuarioRol.getRol());
            }
            usuario.getUsuarioRoles().addAll(usuariosRoles);
            usuarioLocal = usuarioRepositorio.save(usuario);
        }
        return usuarioLocal;
    }

    @Override
    public Usuario obtenerUsuario(String username) {
        return usuarioRepositorio.findByUsername(username);
    }

    @Override
    public void eliminarUsuario(Long usuarioId) {
        usuarioRepositorio.deleteById(usuarioId);
    }

}
