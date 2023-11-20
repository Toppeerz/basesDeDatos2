package co.edu.uniquindio.criterion.repositories;



import co.edu.uniquindio.criterion.model.RegimenHospedaje;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegimenHospedajeRepo extends JpaRepository<RegimenHospedaje, Long> {


    List<RegimenHospedaje> findAllByRegimenHoteles_Hotel_Id(Long id);
}
