package co.edu.uniquindio.criterion.repositories;


import co.edu.uniquindio.criterion.model.CompraReservaAutomovil;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraReservaAutomovilRepo extends JpaRepository<CompraReservaAutomovil, Long> {
}
