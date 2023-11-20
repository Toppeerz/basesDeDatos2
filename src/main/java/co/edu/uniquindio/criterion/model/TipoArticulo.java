package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TipoArticulo implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Debe ingresar la descripcion del tipo de articulo")
    @Column( nullable = false, length = 150)
    private String descripcion;

    @NotEmpty(message = "Debe ingresar el nombre del tipo de articulo")
    @Column(name = "NOMBRE", nullable = false, length = 30)
    private String nombre;

    @OneToMany(mappedBy = "tipoArticulo")
    private List<Articulo> articulos = new ArrayList<>();

}
