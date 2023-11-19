package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
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
public class Pais implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    @NotEmpty(message = "Debe ingresar el nombre del pais")
    @Column( nullable = false, length = 30)
    private String nombre;

    @NotEmpty(message = "Debe ingresar el idioma que se habla en el pais")
    @Column(nullable = false, length = 30)
    private String idioma;

    @NotEmpty(message = "Debe ingresar la moneda del pais")
    @Column( nullable = false, length = 30)
    private String moneda;

    @OneToMany(mappedBy = "pais")
    private List<Ciudad> ciudades = new ArrayList<>();

}