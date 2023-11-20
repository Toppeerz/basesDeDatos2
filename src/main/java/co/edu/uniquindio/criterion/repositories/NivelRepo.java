package co.edu.uniquindio.criterion.repositories;


import co.edu.uniquindio.criterion.model.Nivel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NivelRepo extends JpaRepository<Nivel, Long> {
}
