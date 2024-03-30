package com.example.assessment_inicial.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor

//Definimos la estructura de la tabla con su respectivas validaciones
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Indicamos que es una columna en nuestra tabla
    @Column(nullable = false, length = 255)
    //Validaciones con JSR 380
    @NotBlank(message = "El nombre del usuario es requerido")
    @Size(min = 4, max = 255, message = "El nombre del usuario debe tener al menos 5 caracteres y ser menor a 255")
    private String nombre;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "El apellido paterno del usuario es requerido")
    @Size(min = 4, max = 255, message = "El apellido paterno del usuario debe tener al menos 5 caracteres y ser menor a 50")
    private String apellidoP;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "El apellido materno del usuario es requerido")
    @Size(min = 4, max = 255, message = "El apellido materno del usuario debe tener al menos 5 caracteres y ser menor a 50")
    private String apellidoM;

    @Column(nullable = false)
    @NotNull(message = "La edad del usuario es requerida")
    @Min(value = 15, message = "El usuario debe tener al menos 15 años")
    @Max(value = 70, message = "El usuario no puede tener mas de 70 años")
    private int edad;

    @Email(message = "Debe ingresar una direccion de correo valida")
    private String correo;

    @Column(nullable = false)
    @NotNull(message = "El estatus del usuario es requerido")
    private int activo = 1;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Registro> registro;
}
