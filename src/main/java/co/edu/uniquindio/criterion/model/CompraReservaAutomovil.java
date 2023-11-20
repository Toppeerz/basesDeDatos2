package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CompraReservaAutomovil implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @ManyToOne
    private Cliente cliente;


    @NotNull
    @Column(nullable = false)
    private LocalDate fecha;

    @NotNull
    @ManyToOne
    private MetodoPago metodoPago;

    @NotEmpty(message = "Debe ingresar la descripcion de la reserva del automovil")
    @Column(nullable = false, length = 150)
    private String descripcion;

    @NotNull
    @ManyToOne
    private AlquilerVehiculo alquilerVehiculo;

    @NotNull
    @ManyToOne
    private Vehiculo vehiculo;

    @OneToMany(mappedBy = "compraReservaAutomovil")
    private List<DetalleReservaAutomovil> detallesReservasAutomoviles = new ArrayList<>();

    @ManyToMany(mappedBy = "compraReservaAutomoviles")
    private List<ServiciosAdicionales> serviciosAdicionales = new ArrayList<>();

    //Constructor sin listas ni id
    public CompraReservaAutomovil(@NotNull Cliente cliente, @NotNull LocalDate fecha, @NotNull MetodoPago metodoPago, @NotEmpty(message = "Debe ingresar la descripcion de la reserva del automovil") String descripcion, @NotNull AlquilerVehiculo alquilerVehiculo, @NotNull Vehiculo vehiculo) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
        this.descripcion = descripcion;
        this.alquilerVehiculo = alquilerVehiculo;
        this.vehiculo = vehiculo;
    }
}
