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
public class CompraPaquete implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @ManyToOne
    private Paquete paquete;

    @NotNull
    @ManyToOne
    private Cliente cliente;

    @NotNull
    @ManyToOne
    private Empleado empleado;

    @NotNull
    @ManyToOne
    private MetodoPago metodoPago;

    @Column(nullable = false)
    private LocalDate fecha;

    @NotEmpty(message = "Debe ingresar la descripcion de la compra del paquete")
    @Column(nullable = false, length = 150)
    private String descripcion;

    @OneToMany(mappedBy = "compraPaquete")
    private List<CancelacionPaquete> cancelacionesPaquetes = new ArrayList<>();

    @OneToMany(mappedBy = "compraPaquete")
    private List<DetalleCompraPaquete> detallesComprasPaquetes = new ArrayList<>();

}