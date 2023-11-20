package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;

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
    @Email(message = "Debe ingresar un correo valido")
    private String correo;

    @NotEmpty(message = "Debe ingresar el telefono de la persona")
    @Column( nullable = false, length = 15)
    private String telefono;


    @Column( nullable = false)
    private LocalDate fechaNacimiento;

}
