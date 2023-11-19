package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RegimenHotel implements Serializable {

    @EmbeddedId
    @EqualsAndHashCode.Include
    RegimenHotelLlave id = new RegimenHotelLlave();
    @MapsId("hotelId")
    @ManyToOne
    @JoinColumn(name = "HOTEL_ID", nullable = false)
    private Hotel hotel;

    @MapsId("regimenId")
    @ManyToOne
    @JoinColumn(name = "REGIMEN_ID", nullable = false)
    private RegimenHospedaje regimen;

    @PositiveOrZero
    @Column( nullable = false)
    private Double precio;

}