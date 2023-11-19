package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Agencia implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 30)
    @Column(name = "NOMBRE", length = 30)
    @NotEmpty(message = "Debe ingresar el nombre de la agencia")
    private String nombre;

    @NotNull
    @ManyToOne
    private Ciudad ciudad;

    @NotEmpty(message = "Debe ingresar la descripcion de la agencia")
    @Column(nullable = false, length = 150)
    private String descripcion;


    @NotEmpty(message = "Debe ingresar el telefono de la agencia")
    @Column(nullable = false, length = 15)
    private String telefono;

    @OneToMany(mappedBy = "agencia", cascade = CascadeType.REMOVE)
    private List<Empleado> empleados = new ArrayList<>();

}