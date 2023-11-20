package co.edu.uniquindio.criterion.model;

import lombok.*;


import javax.persistence.*;
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
public class PoliticaDescuento implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "POLITICA_ID", nullable = false)
    private Politica politica;

    @NotNull
    @PositiveOrZero
    private Long cantidadPersonas;

    @NotNull
    @PositiveOrZero
    private Double porcentajeDescuento;

    @OneToMany(mappedBy = "politicaDescuento")
    private List<Paquete> paquetes = new ArrayList<>();

}
