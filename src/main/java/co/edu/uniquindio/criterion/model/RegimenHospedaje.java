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
public class RegimenHospedaje implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Debe ingresar la descripcion del regimen")
    @Column(nullable = false, length = 100)
    private String descripcion;

    @Column( length = 100)
    private String nombreTipo;

    @OneToMany(mappedBy = "regimen")
    private List<RegimenHotel> regimenHoteles = new ArrayList<>();

}