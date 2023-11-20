package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Cliente extends Persona{


    public Cliente(String cedula, String direccion, String nombre, String correo, String telefono, LocalDate fechaNacimiento) {
        super(cedula, direccion, nombre, correo, telefono, fechaNacimiento);
    }

    @OneToMany(mappedBy = "cliente")
    private List<CompraArticulo> comprasArticulos = new ArrayList<>();

    @OneToMany(mappedBy = "cliente")
    private List<CompraPaquete> comprasPaquetes = new ArrayList<>();

    @OneToMany(mappedBy = "cliente")
    private List<CompraReservaAutomovil> comprasReservasAutomoviles = new ArrayList<>();

    @OneToMany(mappedBy = "cliente")
    private List<ReservaHotel> reservaHoteles = new ArrayList<>();

}
