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
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PoliticaCancelacionHotel implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @PositiveOrZero
    private Long diasMaximos;

    @NotNull
    @PositiveOrZero
    private Long costoDiaRetraso;

    @NotEmpty(message = "Debe ingresar la descripcion de la politica de cancelacion de hoteles")
    @Column(nullable = false, length = 150)
    private String descripcion;

    @ManyToMany
    @JoinTable(
            name = "CANCELACIONES_HOTELES",  
            joinColumns = @JoinColumn(name = "POLITICA_CANCELACION_HOTEL_ID"),  
            inverseJoinColumns = @JoinColumn(name = "HOTEL_ID") 
    )
    private List<Hotel> hoteles = new ArrayList<>();

    @OneToMany(mappedBy = "politicaCancelacionHotel")
    private List<CancelacionReserva> cancelacionReservas = new ArrayList<>();
}
