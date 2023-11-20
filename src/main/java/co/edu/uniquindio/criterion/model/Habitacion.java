package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
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
public class Habitacion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @ManyToOne
    private Hotel hotel;

    @NotEmpty(message = "Debe ingresar la descripcion de la habitacion")
    @Column(nullable = false, length = 150)
    private String descripcion;

    @NotEmpty(message = "Debe ingresar el estado de la habitacion")
    @Column(nullable = false, length = 15)
    private String estado;

    @NotNull
    @PositiveOrZero
    private Double precio;

    @NotNull
    @ManyToOne
    private TipoHabitacion tipoHabitacion;

    @NotNull
    @ManyToOne
    private Nivel nivel;

    @OneToMany(mappedBy = "habitacion")
    private List<CompraReserva> compraReservas = new ArrayList<>();

}
