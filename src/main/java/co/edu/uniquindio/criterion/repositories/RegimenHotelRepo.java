package co.edu.uniquindio.criterion.repositories;



import co.edu.uniquindio.criterion.model.RegimenHotel;
import co.edu.uniquindio.criterion.model.RegimenHotelLlave;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegimenHotelRepo extends JpaRepository<RegimenHotel, RegimenHotelLlave> {

    RegimenHotel findByHotel_IdAndRegimen_NombreTipo(Long id, String regimen);
}
