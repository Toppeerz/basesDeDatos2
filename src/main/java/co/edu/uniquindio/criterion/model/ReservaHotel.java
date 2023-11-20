package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReservaHotel implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    private Cliente cliente;

    @NotNull
    @ManyToOne
    private Hotel hotel;


    @Column(nullable = false)
    private LocalDate checkin;

    @PositiveOrZero
    @Column( nullable = false)
    private Double costoMora;


    @Column( nullable = false)
    private LocalDate fechaReserva;


    @Column(nullable = false)
    private LocalDate checkout;

    @NotEmpty(message = "Debe ingresar el estado de la reserva del hotel")
    @Column(nullable = false, length = 15)
    private String estado;

    @PositiveOrZero
    @Column(nullable = false)
    private Double costoTotal;

    @NotNull
    @ManyToOne
    private MetodoPago metodoPago;

    @Size(max = 150)
    @NotNull
    @Column(name = "DESCRIPCION", nullable = false, length = 150)
    private String descripcion;

    @OneToMany(mappedBy = "reservaHotel")
    private List<CancelacionReserva> cancelacionreservas = new ArrayList<>();

    @OneToMany(mappedBy = "reservaHotel")
    private List<CompraReserva> compraReservas = new ArrayList<>();

    @ManyToOne
    @NotNull
    private RegimenHotel regimenHotel;

    //Dame constructor con todos los atributos excepto listas e id
    public ReservaHotel(Cliente cliente, Hotel hotel, LocalDate checkin, Double costoMora, LocalDate fechaReserva, LocalDate checkout, String estado, Double costoTotal, MetodoPago metodoPago, String descripcion, RegimenHotel regimenHotel) {
        this.cliente = cliente;
        this.hotel = hotel;
        this.checkin = checkin;
        this.costoMora = costoMora;
        this.fechaReserva = fechaReserva;
        this.checkout = checkout;
        this.estado = estado;
        this.costoTotal = costoTotal;
        this.metodoPago = metodoPago;
        this.descripcion = descripcion;
        this.regimenHotel = regimenHotel;
    }
    
}
