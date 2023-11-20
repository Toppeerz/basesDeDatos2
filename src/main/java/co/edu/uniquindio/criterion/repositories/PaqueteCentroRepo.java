package co.edu.uniquindio.criterion.repositories;


import co.edu.uniquindio.criterion.model.PaqueteCentro;
import co.edu.uniquindio.criterion.model.PaqueteCentroLlave;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PaqueteCentroRepo extends JpaRepository<PaqueteCentro, PaqueteCentroLlave> {
}
