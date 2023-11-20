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
public class Instalacion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    @NotEmpty(message = "Debe ingresar el nombre de la instalacion")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotEmpty(message = "Debe ingresar el estado de la instalacion")
    @Column(nullable = false, length = 50)
    private String estado;

    @NotEmpty(message = "Debe ingresar la descripcion de la instalacion")
    @Column(nullable = false, length = 150)
    private String descripcion;

    @ManyToMany
    private List<Hotel> hoteles = new ArrayList<>();

}
