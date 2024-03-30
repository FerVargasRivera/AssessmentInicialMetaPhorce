package com.example.assessment_inicial.controller;

import com.example.assessment_inicial.controller.handlers.RegistroNotFoundException;
import com.example.assessment_inicial.model.Registro;
import com.example.assessment_inicial.services.RegistroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegistroController {
    @Autowired
    RegistroService registroService;

    @GetMapping("/Registro")
    public List<Registro> getRegistros() {
        return registroService.obtenRegistros();
    }

    @GetMapping(value="/Registro/Filtrar/{status}")
    List<Registro> filtrar(@PathVariable int status)
    {
        return registroService.filtroRegistro(status);
    }

    @GetMapping("/Registro/{id}")
    Registro getById(@PathVariable Long id) {
        return registroService.obtenRegistro(id).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    @PostMapping("/Registro")
    Registro createNew(@Valid @RequestBody Registro newRegistro) {
        return registroService.guardaRegistro(newRegistro);
    }

    @DeleteMapping("/Registro/{id}")
    String delete(@PathVariable Long id) {
        boolean res = this.registroService.eliminaRegistro(id);
        if(res){
            return "Se eliminó correctamente el registro con el id " + id;
        }else{
            return "No se pudo eliminar el registro con el id " + id;
        }
    }

    @PutMapping("/Registro/{id}")
    Registro updateOrCreate(@Valid @RequestBody Registro newRegistro, @PathVariable Long id) {
        return registroService.actualizaOCreaRegistro(newRegistro, id);
    }
}
