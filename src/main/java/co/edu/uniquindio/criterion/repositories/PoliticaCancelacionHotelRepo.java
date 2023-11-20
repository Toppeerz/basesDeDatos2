package co.edu.uniquindio.criterion.repositories;


import co.edu.uniquindio.criterion.model.PoliticaCancelacionHotel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PoliticaCancelacionHotelRepo extends JpaRepository<PoliticaCancelacionHotel, Long> {
    @Query("SELECT p FROM PoliticaCancelacionHotel p ORDER BY ABS(p.diasMaximos - :diasRetrasados) ASC")
    List<PoliticaCancelacionHotel> findClosestToDayMax(@Param("diasRetrasados") Long diasRetrasados);

}
