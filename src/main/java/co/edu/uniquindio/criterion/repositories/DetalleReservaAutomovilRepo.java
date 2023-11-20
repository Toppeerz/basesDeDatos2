package co.edu.uniquindio.criterion.repositories;


import co.edu.uniquindio.criterion.model.DetalleReservaAutomovil;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleReservaAutomovilRepo extends JpaRepository<DetalleReservaAutomovil, Long> {
}
