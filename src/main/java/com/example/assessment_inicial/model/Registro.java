package com.example.assessment_inicial.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "registros_asistencia")
@Data
@NoArgsConstructor
@AllArgsConstructor

//Definimos la estructura de la tabla con su respectivas validaciones
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "sesion_id")
    private Sesion sesion;

    @Column(nullable = false)
    @NotNull(message = "El estatus del registro es requerido")
    private int activo = 1;
}
