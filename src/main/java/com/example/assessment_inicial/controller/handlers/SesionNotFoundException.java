package com.example.assessment_inicial.controller.handlers;

public class SesionNotFoundException extends RuntimeException{
    public SesionNotFoundException(long id){
        super("No se encontro la sesion con el id " + id);
    }

}
