package co.edu.uniquindio.criterion.model;

import lombok.*;
import org.springframework.security.core.parameters.P;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
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
            name = "CANCELACIONES_HOTELES",  // Nombre personalizado para la tabla intermedia
            joinColumns = @JoinColumn(name = "POLITICA_CANCELACION_HOTEL_ID"),  // Nombre personalizado para la columna que referencia a Estudiante
            inverseJoinColumns = @JoinColumn(name = "HOTEL_ID")  // Nombre personalizado para la columna que referencia a Curso
    )
    private List<Hotel> hoteles = new ArrayList<>();

}