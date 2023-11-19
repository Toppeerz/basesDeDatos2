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
public class RegimenHotelLlave implements Serializable {
    @Column(name = "HOTEL_ID")
    Long hotelId;

    @Column(name = "REGIMEN_ID")
    Long regimenId;
}
