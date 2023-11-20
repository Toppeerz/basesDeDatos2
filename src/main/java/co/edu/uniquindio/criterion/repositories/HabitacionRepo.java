package co.edu.uniquindio.criterion.repositories;




import co.edu.uniquindio.criterion.model.Habitacion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitacionRepo extends JpaRepository<Habitacion, String> {

    List<Habitacion> findAllByHotel_Id(Long id);

    List<Habitacion> findAllByCompraReservas_ReservaHotel_Id(Long id);
}
