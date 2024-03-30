package com.example.assessment_inicial.controller.handlers;

public class RegistroNotFoundException extends RuntimeException{
    public RegistroNotFoundException(long id){
        super("No se encontro el usuario con el id " + id);
    }

}
