package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DetalleCompraPaquete implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @ManyToOne
    private CompraPaquete compraPaquete;

    @PositiveOrZero
    @Column(nullable = false)
    private Long cantidad;

    @NotEmpty(message = "Debe ingresar el estado del Detalle de Compra del Paquete")
    @Column(nullable = false, length = 22)
    private String estado;

    @NotNull
    @PositiveOrZero
    private Double total;

    @PositiveOrZero
    @Column(nullable = false)
    private Long descuento;

}