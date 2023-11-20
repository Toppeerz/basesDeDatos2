package co.edu.uniquindio.criterion.repositories;



import co.edu.uniquindio.criterion.model.ServiciosAdicionales;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ServiciosAdicionalesRepo extends JpaRepository<ServiciosAdicionales, Long> {

    List<ServiciosAdicionales> findAllByCompraReservaAutomoviles_AlquilerVehiculo_Id(Long id);

    List<ServiciosAdicionales>  findAllByCompraReservaAutomoviles_Id(Long id);
}
