package co.edu.uniquindio.criterion.repositories;


import co.edu.uniquindio.criterion.model.AlquilerVehiculo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlquilerVehiculoRepo extends JpaRepository<AlquilerVehiculo, Long> {
}
