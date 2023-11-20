package co.edu.uniquindio.criterion.repositories;


import co.edu.uniquindio.criterion.model.PoliticaCancelacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PoliticaCancelacionRepo extends JpaRepository<PoliticaCancelacion, Long> {
}
