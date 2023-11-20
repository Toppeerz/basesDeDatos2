package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Marca implements Serializable {
    @Id
    @NotEmpty(message = "Debe ingresar el nombre de la marca")
    @Column( nullable = false, length = 30)
    @EqualsAndHashCode.Include
    private String nombre;

    @NotEmpty(message = "Debe ingresar la descripcion de la marca")
    @Column(nullable = false, length = 150)
    private String descripcion;

    @OneToMany(mappedBy = "nombreMarca")
    private List<Vehiculo> vehiculos = new ArrayList<>();

}
