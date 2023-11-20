package co.edu.uniquindio.criterion.repositories;


import co.edu.uniquindio.criterion.model.Paquete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PaqueteRepo extends JpaRepository<Paquete, Long> {
}
