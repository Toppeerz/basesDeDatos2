package co.edu.uniquindio.criterion.repositories;


import co.edu.uniquindio.criterion.model.MetodoPago;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MetodoPagoRepo extends JpaRepository<MetodoPago, Long> {

    MetodoPago findByNombre(String metodoPago);
}
