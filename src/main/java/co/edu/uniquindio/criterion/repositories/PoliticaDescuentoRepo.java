package co.edu.uniquindio.criterion.repositories;



import co.edu.uniquindio.criterion.model.PoliticaDescuento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PoliticaDescuentoRepo extends JpaRepository<PoliticaDescuento, Long> {
}
