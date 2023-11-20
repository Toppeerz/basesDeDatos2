package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
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

public class AlquilerVehiculo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @ManyToOne
    private Ciudad ciudadRecepcion;

    @NotNull
    @ManyToOne
    private Ciudad ciudadOrigen;

    @Column( nullable = false)
    private LocalDate fechaReserva;

    @Column( nullable = false)
    private LocalDate fechaEntrega;

    @PositiveOrZero
    @Column(nullable = false)
    private Double precio;

    @OneToMany(mappedBy = "alquilerVehiculo")
    private List<CompraReservaAutomovil> comprasReservasAutomoviles = new ArrayList<>();

    //Constructor sin listas ni id
    public AlquilerVehiculo(@NotNull Ciudad ciudadRecepcion, @NotNull Ciudad ciudadOrigen, LocalDate fechaReserva, LocalDate fechaEntrega, @PositiveOrZero Double precio) {
        this.ciudadRecepcion = ciudadRecepcion;
        this.ciudadOrigen = ciudadOrigen;
        this.fechaReserva = fechaReserva;
        this.fechaEntrega = fechaEntrega;
        this.precio = precio;
    }

}
