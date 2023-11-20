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
public class TipoPaquete implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Debe ingresar el nombre del tipo de paquete")
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @OneToMany(mappedBy = "tipoPaquete")
    private List<Paquete> paquetes = new ArrayList<>();

}
