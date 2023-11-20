package co.edu.uniquindio.criterion.repositories;





import co.edu.uniquindio.criterion.model.Vehiculo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VehiculoRepo extends JpaRepository<Vehiculo, Long> {

    Vehiculo findByCompraReservaAutomovils_AlquilerVehiculo_Id(Long id);
}
