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
    private Double precioadicional;

    @ManyToMany
    @JoinTable(
            name = "SERVICIOS_RESERVAS_AUTOS",  // Nombre personalizado para la tabla intermedia
            joinColumns = @JoinColumn(name = "SERVICIO_ID"),  // Nombre personalizado para la columna que referencia a Estudiante
            inverseJoinColumns = @JoinColumn(name = "COMPRA_RESERVA_AUTO_ID")  // Nombre personalizado para la columna que referencia a Curso
    )
    private List<CompraReservaAutomovil> compraReservaAutomoviles = new ArrayList<>();

}