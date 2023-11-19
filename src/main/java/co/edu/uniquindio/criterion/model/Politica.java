package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Politica implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Debe ingresar la descripcion de la politica")
    @Column( nullable = false, length = 150)
    private String descripcion;

    @NotEmpty(message = "Debe ingresar el nombre de la politica")
    @Column(nullable = false, length = 30)
    private String nombre;

    @OneToOne(mappedBy = "politica")
    private PoliticaDescuento politicaDescuento;

    @OneToOne(mappedBy = "politica")
    private PoliticaCancelacion politicaCancelacion;

}