package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
public class CentroTuristico implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @ManyToOne
    private Ciudad ciudad;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "Debe ingresar la direccion del centro turistico")
    private String direccion;


    @Column(nullable = false, length = 30)
    @NotEmpty(message = "Debe ingresar el nombre del centro turistico")
    private String nombre;

    @OneToMany(mappedBy = "centroTuristico")
    private List<PaqueteCentro> paquetesCentro = new ArrayList<>();

}