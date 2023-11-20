package co.edu.uniquindio.criterion.repositories;



import co.edu.uniquindio.criterion.model.CancelacionPaquete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancelacionPaqueteRepo extends JpaRepository<CancelacionPaquete, Long> {
}
