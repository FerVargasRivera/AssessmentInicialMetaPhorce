package com.example.assessment_inicial.controller;

import com.example.assessment_inicial.controller.handlers.UsuarioNotFoundException;
import com.example.assessment_inicial.services.UsuarioService;
import com.example.assessment_inicial.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/Usuario")
    public List<Usuario> getUsuarios() {
        return usuarioService.obtenUsuarios();
    }

    @GetMapping(value="/Usuario/Filtrar/{status}")
    List<Usuario> filtrar(@PathVariable int status)
    {
        return usuarioService.filtroUsuario(status);
    }

    @GetMapping("/Usuario/{id}")
    Usuario getById(@PathVariable Long id) {
        return usuarioService.obtenUsuario(id).orElseThrow(() -> new UsuarioNotFoundException(id));
    }

    @PostMapping("/Usuario")
    Usuario createNew(@Valid @RequestBody Usuario newUsuario) {
        return usuarioService.guardaUsuario(newUsuario);
    }

    @DeleteMapping("/Usuario/{id}")
    String delete(@PathVariable Long id) {
        boolean res = this.usuarioService.eliminaUsuario(id);
        if(res){
            return "Se eliminó correctamente el usuario con el id " + id;
        }else{
            return "No se pudo eliminar el usuario con el id " + id;
        }
    }

    @PutMapping("/Usuario/{id}")
    Usuario updateOrCreate(@Valid @RequestBody Usuario newUsuario, @PathVariable Long id) {
        return usuarioService.actualizaOCreaUsuario(newUsuario, id);
    }
}
