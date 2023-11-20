package co.edu.uniquindio.criterion.repositories;





import co.edu.uniquindio.criterion.model.TipoHotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TipoHotelRepo extends JpaRepository<TipoHotel, String> {
}
