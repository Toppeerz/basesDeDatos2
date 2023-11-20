package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PaqueteCentro implements Serializable {

    @EmbeddedId
    @EqualsAndHashCode.Include
    PaqueteCentroLlave id = new PaqueteCentroLlave();
    @MapsId("centroId")
    @ManyToOne
    @JoinColumn(name = "CENTROTURISTICO_ID", nullable = false)
    private CentroTuristico centroTuristico;

    @MapsId("paqueteId")
    @ManyToOne
    @JoinColumn(name = "PAQUETE_ID", nullable = false)
    private Paquete paquete;

    @Column( length = 150)
    private String descripcion;

}
