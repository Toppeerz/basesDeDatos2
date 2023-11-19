package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
public class Empleado extends Persona {

    @NotNull
    @ManyToOne
    private Agencia agencia;

    @NotNull
    @ManyToOne
    private TipoEmpleado tipoEmpleado;

    @OneToMany(mappedBy = "empleado")
    private List<CompraArticulo> comprasArticulos = new ArrayList<>();

    @OneToMany(mappedBy = "empleado")
    private List<CompraPaquete> comprasPaquetes = new ArrayList<>();

    @OneToMany(mappedBy = "empleado")
    private List<CompraReserva> comprasReservas = new ArrayList<>();

    @OneToMany(mappedBy = "empleado")
    private List<CompraReservaAutomovil> comprasReservasAutomoviles = new ArrayList<>();

}