package co.edu.uniquindio.criterion.repositories;





import co.edu.uniquindio.criterion.model.TipoVehiculo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TipoVehiculoRepo extends JpaRepository<TipoVehiculo, String> {
}
