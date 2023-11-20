package co.edu.uniquindio.criterion.repositories;


import co.edu.uniquindio.criterion.model.CentroTuristico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentroTuristicoRepo extends JpaRepository<CentroTuristico, Long> {
}
