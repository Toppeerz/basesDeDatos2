package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Paquete implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    @NotEmpty(message = "Debe ingresar el nombre del paquete")
    @Column( nullable = false, length = 100)
    private String nombre;

    @NotNull
    @ManyToOne
    private TipoPaquete tipoPaquete;

    @NotNull
    @PositiveOrZero
    @Column( nullable = false)
    private Long cantidadPersonas;

    @NotEmpty(message = "Debe ingresar la descripcion del paquete")
    @Column(name = "DESCRIPCION", nullable = false, length = 150)
    private String descripcion;

    @PositiveOrZero
    @Column( nullable = false)
    private Double precio;


    @Column( nullable = false)
    private LocalDate fechacreacion;

    @NotNull
    @ManyToOne
    @JoinTable(name = "CANCELACION_ID")
    private PoliticaCancelacion politicaCancelacion;

    @NotNull
    @ManyToOne
    @JoinTable(name = "DESCUENTO_ID")
    private PoliticaDescuento politicaDescuento;

    @OneToMany(mappedBy = "paquete")
    private List<CompraPaquete> compraPaquetes = new ArrayList<>();

    @OneToMany(mappedBy = "paquete")
    private List<PaqueteCentro> paqueteCentros = new ArrayList<>();

}