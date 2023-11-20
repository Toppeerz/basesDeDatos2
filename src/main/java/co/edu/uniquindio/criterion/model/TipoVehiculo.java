package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
public class TipoVehiculo implements Serializable {
    @Id
    @Size(max = 50)
    @Column( nullable = false, length = 50)
    private String tipo;

    @NotEmpty(message = "Debe ingresar la descripcion del tipo de vehiculo")
    @Column(name = "DESCRIPCION", nullable = false, length = 150)
    private String descripcion;

    @NotNull
    @ManyToOne
    private TipoGama tipoGama;

    @OneToMany(mappedBy = "tipoVehiculo")
    private List<Vehiculo> vehiculos = new ArrayList<>();

}
