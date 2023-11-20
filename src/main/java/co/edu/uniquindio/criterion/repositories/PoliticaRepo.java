package co.edu.uniquindio.criterion.repositories;


import co.edu.uniquindio.criterion.model.Politica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PoliticaRepo extends JpaRepository<Politica, Long> {
}
