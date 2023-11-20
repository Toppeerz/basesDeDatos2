package co.edu.uniquindio.criterion.repositories;




import co.edu.uniquindio.criterion.model.TipoHabitacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TipoHabitacionRepo extends JpaRepository<TipoHabitacion, String> {
}
