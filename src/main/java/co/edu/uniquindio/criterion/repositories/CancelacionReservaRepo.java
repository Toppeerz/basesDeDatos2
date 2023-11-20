package co.edu.uniquindio.criterion.repositories;



import co.edu.uniquindio.criterion.model.CancelacionReserva;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancelacionReservaRepo extends JpaRepository<CancelacionReserva, Long> {
}
