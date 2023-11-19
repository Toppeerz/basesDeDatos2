package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TipoHotel implements Serializable {
    @Id
    @Size(max = 100)
    @Column(name = "TIPO", nullable = false, length = 100)
    private String tipo;

    @NotEmpty(message = "Debe ingresar la descripcion del tipo de hotel")
    @Column(name = "DESCRIPCION", nullable = false, length = 150)
    private String descripcion;

    @NotEmpty(message = "Debe ingresar el nombre del tipo de hotel")
    @Column(name = "NOMBRE", nullable = false, length = 30)
    private String nombre;

    @OneToMany(mappedBy = "tipoHotel")
    private List<Hotel> hoteles = new ArrayList<>();

}