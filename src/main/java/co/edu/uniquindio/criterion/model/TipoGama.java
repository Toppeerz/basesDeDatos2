package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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
public class TipoGama implements Serializable {
    @Id
    @Size(max = 30)
    @Column(name = "TIPO", nullable = false, length = 30)
    private String tipo;

    @NotEmpty(message = "Debe ingresar la descripcion del tipo de gama")
    @Column(name = "DESCRIPCION", nullable = false, length = 150)
    private String descripcion;

    @OneToMany(mappedBy = "tipoGama")
    private List<TipoVehiculo> tipoVehiculos = new ArrayList<>();

}
