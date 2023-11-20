package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DetalleCompraArticulo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    @Positive
    @Min(value = 1)
    @Column(nullable = false)
    private Long unidades;

    @NotNull
    @ManyToOne
    private CompraArticulo compraArticulo;

    @PositiveOrZero
    @Column( nullable = false)
    private Double precioUnidad;


    @Column( nullable = false)
    @PositiveOrZero
    private Double total;

}
