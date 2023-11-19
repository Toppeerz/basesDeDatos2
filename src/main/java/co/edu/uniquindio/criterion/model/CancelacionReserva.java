package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
public class CancelacionReserva implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;


    @Column( nullable = false)
    private LocalDate fechaCancelacion;

    @NotNull
    @PositiveOrZero
    private Double costo;


    @Column(nullable = false, length = 150)
    @NotEmpty(message = "Debe ingresar el motivo de la cancelacion")
    private String motivo;

    @ManyToOne
    @NotNull
    private ReservaHotel reservaHotel;

}