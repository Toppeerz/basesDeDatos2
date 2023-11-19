package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
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
public class PoliticaCancelacion implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "ID", nullable = false)
    private Politica politica;

    @NotNull
    @PositiveOrZero
    private Double costo;

    @NotNull
    @PositiveOrZero
    private Long diasMaximos;

    @NotNull
    @PositiveOrZero
    private Long costoRetraso;

    @OneToMany(mappedBy = "politicaCancelacion")
    private List<Paquete> paquetes = new ArrayList<>();

}