package co.edu.uniquindio.criterion.repositories;


import co.edu.uniquindio.criterion.model.PoliticaCancelacion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PoliticaCancelacionRepo extends JpaRepository<PoliticaCancelacion, Long> {
    @Query("SELECT p FROM PoliticaCancelacion p ORDER BY ABS(p.diasMaximos - :diasRetrasados) ASC")
    List<PoliticaCancelacion> findClosestToDayMax(@Param("diasRetrasados") Long diasRetrasados);
}
