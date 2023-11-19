package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente extends Persona{

    @OneToMany(mappedBy = "cliente")
    private List<CompraArticulo> comprasArticulos = new ArrayList<>();

    @OneToMany(mappedBy = "cliente")
    private List<CompraPaquete> comprasPaquetes = new ArrayList<>();

    @OneToMany(mappedBy = "cliente")
    private List<CompraReservaAutomovil> comprasReservasAutomoviles = new ArrayList<>();

    @OneToMany(mappedBy = "cliente")
    private List<ReservaHotel> reservaHoteles = new ArrayList<>();

}