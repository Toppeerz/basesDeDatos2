package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DetalleReservaAutomovil implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    @PositiveOrZero
    @Column(nullable = false)
    private Long costoTotal;

    @Column(nullable = false)
    private LocalDate fecha;

    @PositiveOrZero
    @Column(nullable = false)
    private Long cantidad;

    @NotNull
    @ManyToOne
    private CompraReservaAutomovil compraReservaAutomovil;


    @Column(nullable = false)
    @PositiveOrZero
    private Long alquilerId;

    @PositiveOrZero
    @Column(nullable = false)
    private Long vehiculoId;

}
