package co.edu.uniquindio.criterion.repositories;



import co.edu.uniquindio.criterion.model.ReservaHotel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReservaHotelRepo extends JpaRepository<ReservaHotel, Long> {

    List<ReservaHotel> findAllByCliente_Cedula(String cedula);
}
