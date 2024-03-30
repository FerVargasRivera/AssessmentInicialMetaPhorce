package com.example.assessment_inicial.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Entity
@Table(name = "sesiones")
@Data
@NoArgsConstructor
@AllArgsConstructor

//Definimos la estructura de la tabla con su respectivas validaciones
public class Sesion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Indicamos que es una columna en nuestra tabla
    @Column(nullable = false, length = 255)
    //Validaciones con JSR 380
    @NotBlank(message = "El titulo de la sesion es requerido")
    @Size(min = 4, max = 255, message = "El titulo de la sesion debe tener al menos 4 caracteres y ser menor a 255")
    private String titulo;

    @Column(nullable = false)
    @NotBlank(message = "La descripcion de la sesion es obligatoria")
    private String descripcion;

    @Future(message = "La fecha de la sesión debe ser en el futuro")
    private LocalDate fecha;

    private LocalTime horaInicio;

    private LocalTime horaFin;

    @Column(nullable = false)
    @NotNull(message = "El estatus de la sesion es requerido")
    private int activo = 1;

    @OneToMany(mappedBy = "sesion", cascade = CascadeType.ALL)
    private List<Registro> registro;
}
