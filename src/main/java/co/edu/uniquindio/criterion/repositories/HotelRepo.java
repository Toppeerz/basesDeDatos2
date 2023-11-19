package co.edu.uniquindio.criterion.repositories;

import co.edu.uniquindio.criterion.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Long> {
}
