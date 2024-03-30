package com.example.assessment_inicial.controller.handlers;

public class UsuarioNotFoundException extends RuntimeException{
    public UsuarioNotFoundException(long id){
        super("No se encontro el usuario con el id " + id);
    }

}
