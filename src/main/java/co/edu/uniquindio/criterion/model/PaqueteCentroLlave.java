package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PaqueteCentroLlave implements Serializable {

    @Column(name = "PAQUETE_ID")
    Long paqueteId;

    @Column(name = "CENTROTURISTICO_ID")
    Long centroId;
}
