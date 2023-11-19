package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Articulo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    @NotEmpty(message = "Debe ingresar el nombre del articulo")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotEmpty(message = "Debe ingresar la descripcion del articulo")
    @Column(nullable = false, length = 100)
    private String descripcion;

    @PositiveOrZero
    @Column( nullable = false)
    private Double precio;

    @ManyToOne
    @NotNull
    private TipoArticulo tipoArticulo;

    @OneToMany(mappedBy = "articulo")
    private List<CompraArticulo> compraArticulos = new ArrayList<>();

}