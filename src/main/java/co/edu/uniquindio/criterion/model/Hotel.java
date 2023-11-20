package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
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
public class Hotel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @ManyToOne
    private Ciudad ciudad;

    @NotEmpty(message = "Se debe ingresar el nombre del hotel")
    @Column(nullable = false, length = 100)
    private String nombre;


    @PositiveOrZero
    @Max(value = 5)
    private Long valoracion;

    @NotNull
    @ManyToOne
    private TipoHotel tipoHotel;

    @NotEmpty(message = "Debe ingresar la direccion del hotel")
    @Column(nullable = false, length = 50)
    private String direccion;

    @OneToMany(mappedBy = "hotel")
    private List<Habitacion> habitaciones = new ArrayList<>();

    @ManyToMany(mappedBy = "hoteles")
    private List<Instalacion> instalaciones = new ArrayList<>();

    @ManyToMany(mappedBy = "hoteles")
    private List<PoliticaCancelacionHotel> politicaCancelacionHoteles = new ArrayList<>();

    @OneToMany(mappedBy = "hotel")
    private List<RegimenHotel> regimenHoteles = new ArrayList<>();

    @OneToMany(mappedBy = "hotel")
    private List<ReservaHotel> reservaHoteles = new ArrayList<>();

}
