package co.edu.uniquindio.criterion.repositories;


import co.edu.uniquindio.criterion.model.Ciudad;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CiudadRepo extends JpaRepository<Ciudad, Long> {

    Ciudad findByNombre(String nombre);

    List<Ciudad> findByPais_Nombre(String nombre);
}
