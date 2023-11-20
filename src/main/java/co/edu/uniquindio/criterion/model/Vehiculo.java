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
public class Vehiculo implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @PositiveOrZero
    @Column(nullable = false)
    private Long capacidadPersonas;

    @NotEmpty(message = "Debe ingresar si el vehiculo se encuentra disponible")
    @Column(name = "DISPONIBLE", nullable = false, length = 2)
    private String disponible;

    @NotEmpty(message = "Debe ingresar el estado del vehiculo")
    @Column(name = "ESTADO", nullable = false, length = 20)
    private String estado;

    @NotNull
    @ManyToOne
    private TipoVehiculo tipoVehiculo;

    @NotNull
    @ManyToOne
    private Marca nombreMarca;

    @PositiveOrZero
    @NotNull
    private Double precioDia;

    @OneToMany(mappedBy = "vehiculo")
    private List<CompraReservaAutomovil> compraReservaAutomovils = new ArrayList<>();

}
