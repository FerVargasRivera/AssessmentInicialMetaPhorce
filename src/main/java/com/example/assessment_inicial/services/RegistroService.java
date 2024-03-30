package com.example.assessment_inicial.services;

import com.example.assessment_inicial.model.Registro;
import com.example.assessment_inicial.persistence.RegistroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistroService {
    @Autowired //Injectar las dependencias necesarias
    RegistroRepository registroRepository;
    //Metodos necesarios para consultar, editar y eliminar los registros
    public List<Registro> obtenRegistros(){
        return registroRepository.findAll();
    }

    public List<Registro> filtroRegistro(int status)
    {
        return registroRepository.findByActivo(status);
    }

    public Optional<Registro> obtenRegistro(long id) {
        return registroRepository.findById(id);
    }

    @Transactional
    public Registro guardaRegistro(Registro newRegistro) {
        return registroRepository.save(newRegistro);
    }

    public Boolean eliminaRegistro(long id) {
        if(registroRepository.findById(id).isPresent())
        {
            registroRepository.findById(id)
                    .map(registro -> {
                        registro.setActivo(0);
                        return  registroRepository.save(registro);
                    });
            return true;
        }
        else
        {
            return false;
        }
    }

    public Registro actualizaOCreaRegistro(Registro newRegistro, Long id) {

        return registroRepository.findById(id)
                .map(registro -> {
                    registro.setUsuario(newRegistro.getUsuario());
                    registro.setSesion(newRegistro.getSesion());
                    return registroRepository.save(registro);
                })
                .orElseGet(() -> {
                    newRegistro.setId(id);
                    return registroRepository.save(newRegistro);
                });
    }
}
