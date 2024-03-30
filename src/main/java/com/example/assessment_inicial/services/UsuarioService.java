package com.example.assessment_inicial.services;

import com.example.assessment_inicial.model.Usuario;
import com.example.assessment_inicial.persistence.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired //Injectar las dependencias necesarias
    UsuarioRepository usuarioRepository;
    //Metodos necesarios para consultar, editar y eliminar los registros
    public List<Usuario> obtenUsuarios(){
        return usuarioRepository.findAll();
    }

    public List<Usuario> filtroUsuario(int status)
    {
        return usuarioRepository.findByActivo(status);
    }

    public Optional<Usuario> obtenUsuario(long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario guardaUsuario(Usuario newUsuario) {
        return usuarioRepository.save(newUsuario);
    }

    public Boolean eliminaUsuario(long id) {
        if(usuarioRepository.findById(id).isPresent())
        {
            usuarioRepository.findById(id)
                    .map(usuario -> {
                        usuario.setActivo(0);
                        return  usuarioRepository.save(usuario);
                    });
            return true;
        }
        else
        {
            return false;
        }
    }

    public Usuario actualizaOCreaUsuario(Usuario newUsuario, Long id) {

        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setNombre(newUsuario.getNombre());
                    usuario.setApellidoP(newUsuario.getApellidoP());
                    usuario.setApellidoM(newUsuario.getApellidoM());
                    usuario.setEdad(newUsuario.getEdad());
                    usuario.setCorreo(newUsuario.getCorreo());
                    return usuarioRepository.save(usuario);
                })
                .orElseGet(() -> {
                    newUsuario.setId(id);
                    return usuarioRepository.save(newUsuario);
                });
    }
}
