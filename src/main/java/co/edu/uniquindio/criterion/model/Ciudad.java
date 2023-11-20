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
public class Ciudad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    @NotEmpty(message = "Debe ingresar el nombre de la ciudad")
    @Column(nullable = false, length = 30)
    private String nombre;

    @Column(nullable = true)
    @PositiveOrZero
    private Long codigoPostal;

    @NotNull
    @ManyToOne
    private Pais pais;

    @OneToMany(mappedBy = "ciudad")
    private List<Agencia> agencias = new ArrayList<>();

    @OneToMany(mappedBy = "ciudadRecepcion")
    private List<AlquilerVehiculo> alquilerVehiculosRecepcion = new ArrayList<>();

    @OneToMany(mappedBy = "ciudadOrigen")
    private List<AlquilerVehiculo> alquilerVehiculosOrigen = new ArrayList<>();

    @OneToMany(mappedBy = "ciudad")
    private List<CentroTuristico> centrosTuristicos = new ArrayList<>();

    @OneToMany(mappedBy = "ciudad")
    private List<Hotel> hoteles = new ArrayList<>();

}
