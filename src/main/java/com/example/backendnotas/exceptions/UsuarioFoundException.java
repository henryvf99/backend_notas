package com.example.backendnotas.exceptions;

public class UsuarioFoundException extends Exception{

    public UsuarioFoundException(){
        super("El usuario con ese username ya existe en la base de datos, vuelva a intentarlo");
    }

    public UsuarioFoundException(String mensaje){
        super(mensaje);
    }

}
