package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CompraReserva implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @ManyToOne
    private Habitacion habitacion;

    @NotNull
    @ManyToOne
    private ReservaHotel reservaHotel;


    @Column(nullable = false)
    private LocalDate fecha;

    public CompraReserva( Habitacion habitacion, ReservaHotel reservaHotel, LocalDate fecha) {
        this.habitacion = habitacion;
        this.reservaHotel = reservaHotel;
        this.fecha = fecha;
    }
    
}
