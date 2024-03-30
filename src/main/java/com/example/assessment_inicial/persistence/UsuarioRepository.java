package com.example.assessment_inicial.persistence;

import com.example.assessment_inicial.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    //Creamos el filtro para consultar los activos en nuestro servicio
    List<Usuario> findByActivo(int activo);
}
