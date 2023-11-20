package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
public class ServiciosAdicionales implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Debe ingresar el nombre del servicio adicional")
    @Column( nullable = false, length = 100)
    private String nombre;

    @NotEmpty(message = "Debe ingresar la descripcion del servicio adicional")
    @Column( nullable = false, length = 100)
    private String descripcion;

    @PositiveOrZero
    @Column( nullable = false)
    private Double precioAdicional;

    @ManyToMany
    @JoinTable(
            name = "SERVICIOS_RESERVAS_AUTOS",  
            joinColumns = @JoinColumn(name = "SERVICIO_ID"),  
            inverseJoinColumns = @JoinColumn(name = "COMPRA_RESERVA_AUTO_ID")  
    )
    private List<CompraReservaAutomovil> compraReservaAutomoviles = new ArrayList<>();

}
