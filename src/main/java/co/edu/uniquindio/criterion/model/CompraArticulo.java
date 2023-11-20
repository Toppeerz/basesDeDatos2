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
public class CompraArticulo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    @PositiveOrZero
    @NotNull
    private Double precioVenta;

    @NotNull
    @ManyToOne
    private Cliente cliente;

    @NotNull
    @ManyToOne
    private Articulo articulo;

    @NotNull
    @ManyToOne
    private MetodoPago metodoPago;


    @Column(nullable = false)
    private LocalDate fecha;


    @OneToMany(mappedBy = "compraArticulo")
    private List<DetalleCompraArticulo> detallesComprasArticulos = new ArrayList<>();

}
