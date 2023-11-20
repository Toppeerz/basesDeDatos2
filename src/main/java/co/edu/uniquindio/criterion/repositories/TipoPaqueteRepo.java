package co.edu.uniquindio.criterion.repositories;






import co.edu.uniquindio.criterion.model.TipoPaquete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TipoPaqueteRepo extends JpaRepository<TipoPaquete, Long> {
}
