package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @Column(length = 10)
    private String cedula;

    @NotEmpty(message = "Debe ingresar la direccion de la persona")
    @Column(nullable = false, length = 50)
    private String direccion;

    @NotEmpty(message = "Debe ingresar el nombre de la persona")
    @Column( nullable = false, length = 30)
    private String nombre;

    @NotEmpty(message = "Debe ingresar el correo de la persona")
    @Column(nullable = false, length = 60)
    private String correo;

    @NotEmpty(message = "Debe ingresar el telefono de la persona")
    @Column( nullable = false, length = 15)
    private String telefono;


    @Column( nullable = false)
    private LocalDate fechaNacimiento;

}