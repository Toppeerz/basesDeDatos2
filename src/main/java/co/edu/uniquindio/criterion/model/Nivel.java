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
public class Nivel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    @NotEmpty(message = "Debe ingresar el nombre del nivel")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotEmpty(message = "Debe ingresar la descripcion del nivel")
    @Column(name = "DESCRIPCION", nullable = false, length = 100)
    private String descripcion;

    @OneToMany(mappedBy = "nivel")
    private List<Habitacion> habitacions = new ArrayList<>();

}
