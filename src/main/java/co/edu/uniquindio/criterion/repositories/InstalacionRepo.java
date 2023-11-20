package co.edu.uniquindio.criterion.repositories;


import co.edu.uniquindio.criterion.model.Instalacion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface InstalacionRepo extends JpaRepository<Instalacion, Long> {
    List<Instalacion> findAllByHoteles_Id(Long id);
}
