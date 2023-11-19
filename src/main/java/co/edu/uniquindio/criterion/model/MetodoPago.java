package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
public class MetodoPago implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    @NotEmpty(message = "Debe ingresar el nombre del metodo de pago")
    @Column(nullable = false, length = 30)
    private String nombre;

    @NotEmpty(message = "Debe ingresar la descripcion del metodo de pago")
    @Column(nullable = false, length = 150)
    private String descripcion;

    @NotEmpty(message = "Debe ingresar el estado del metodo de pago")
    @Column(nullable = false, length = 15)
    private String estado;

    @OneToMany(mappedBy = "metodoPago")
    private List<CompraArticulo> comprasArticulos = new ArrayList<>();

    @OneToMany(mappedBy = "metodoPago")
    private List<CompraPaquete> compraPaquetes = new ArrayList<>();

    @OneToMany(mappedBy = "metodoPago")
    private List<CompraReservaAutomovil> compraReservaAutomoviles = new ArrayList<>();

    @OneToMany(mappedBy = "metodoPago")
    private List<ReservaHotel> reservahoteles = new ArrayList<>();

}