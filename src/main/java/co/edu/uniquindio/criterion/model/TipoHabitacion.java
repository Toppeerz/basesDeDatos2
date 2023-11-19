package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
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
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TipoHabitacion implements Serializable {
    @Id
    @Size(max = 100)
    @Column(name = "TIPO", nullable = false, length = 30)
    private String tipo;

    @NotEmpty(message = "Debe ingresar el nombre del tipo de habitacion")
    @Column(name = "NOMBRE", nullable = false, length = 30)
    private String nombre;

    @PositiveOrZero
    @Column(name = "CAPACIDAD", nullable = false)
    private Long capacidad;

    @OneToMany(mappedBy = "tipoHabitacion")
    private List<Habitacion> habitaciones = new ArrayList<>();

}