package com.example.assessment_inicial.services;

import com.example.assessment_inicial.model.Sesion;
import com.example.assessment_inicial.persistence.SesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SesionService {
    @Autowired //Injectar las dependencias necesarias
    SesionRepository sesionRepository;
    //Metodos necesarios para consultar, editar y eliminar los registros
    public List<Sesion> obtenSesiones(){
        return sesionRepository.findAll();
    }

    public List<Sesion> filtroSesion(int status)
    {
        return sesionRepository.findByActivo(status);
    }

    public Optional<Sesion> obtenSesion(long id) {
        return sesionRepository.findById(id);
    }

    public Sesion guardaSesion(Sesion newSesion) {
        return sesionRepository.save(newSesion);
    }

    public Boolean eliminaSesion(long id) {
        if(sesionRepository.findById(id).isPresent())
        {
            sesionRepository.findById(id)
                    .map(sesion -> {
                        sesion.setActivo(0);
                        return  sesionRepository.save(sesion);
                    });
            return true;
        }
        else
        {
            return false;
        }
    }

    public Sesion actualizaOCreaSesion(Sesion newSesion, Long id) {

        return sesionRepository.findById(id)
                .map(sesion -> {
                    sesion.setTitulo(newSesion.getTitulo());
                    sesion.setDescripcion(newSesion.getDescripcion());
                    sesion.setFecha(newSesion.getFecha());
                    sesion.setHoraInicio(newSesion.getHoraInicio());
                    sesion.setHoraFin(newSesion.getHoraFin());
                    return sesionRepository.save(sesion);
                })
                .orElseGet(() -> {
                    newSesion.setId(id);
                    return sesionRepository.save(newSesion);
                });
    }
}
