package co.edu.uniquindio.criterion.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
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
public class TipoEmpleado implements Serializable {
    @Id
    @Size(max = 50)
    @Column(name = "TIPO", nullable = false, length = 50)
    private String tipo;

    @PositiveOrZero
    @Column( nullable = false)
    private Double salario;

    @OneToMany(mappedBy = "tipoEmpleado")
    private List<Empleado> empleados = new ArrayList<>();

}