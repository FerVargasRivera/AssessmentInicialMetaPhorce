package com.example.assessment_inicial.persistence;

import com.example.assessment_inicial.model.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {
    List<Registro> findByActivo(int activo);

}
