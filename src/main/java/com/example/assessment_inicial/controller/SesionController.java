package com.example.assessment_inicial.controller;

import com.example.assessment_inicial.controller.handlers.SesionNotFoundException;
import com.example.assessment_inicial.model.Sesion;
import com.example.assessment_inicial.services.SesionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SesionController {
    @Autowired
    SesionService sesionService;

    @GetMapping("/Sesion")
    public List<Sesion> getSesiones() {
        return sesionService.obtenSesiones();
    }

    @GetMapping(value="/Sesion/Filtrar/{status}")
    List<Sesion> filtrar(@PathVariable int status)
    {
        return sesionService.filtroSesion(status);
    }

    @GetMapping("/Sesion/{id}")
    Sesion getById(@PathVariable Long id) {
        return sesionService.obtenSesion(id).orElseThrow(() -> new SesionNotFoundException(id));
    }

    @PostMapping("/Sesion")
    Sesion createNew(@Valid @RequestBody Sesion newSesion) {
        return sesionService.guardaSesion(newSesion);
    }

    @DeleteMapping("/Sesion/{id}")
    String delete(@PathVariable Long id) {
        boolean res = this.sesionService.eliminaSesion(id);
        if(res){
            return "Se eliminó correctamente la sesion con el id " + id;
        }else{
            return "No se pudo eliminar la sesion con el id " + id;
        }
    }

    @PutMapping("/Sesion/{id}")
    Sesion updateOrCreate(@Valid @RequestBody Sesion newSesion, @PathVariable Long id) {
        return sesionService.actualizaOCreaSesion(newSesion, id);
    }
}
