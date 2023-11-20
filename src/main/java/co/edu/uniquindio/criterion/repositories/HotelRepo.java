package co.edu.uniquindio.criterion.repositories;

import co.edu.uniquindio.criterion.model.Hotel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HotelRepo extends JpaRepository<Hotel, Long> {

    List<Hotel> findAllByNombre(String nombre);

    List<Hotel> findByCiudad_Nombre(String nombre);
}
