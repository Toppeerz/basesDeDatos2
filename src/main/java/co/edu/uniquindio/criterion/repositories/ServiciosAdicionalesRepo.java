package co.edu.uniquindio.criterion.repositories;



import co.edu.uniquindio.criterion.model.ServiciosAdicionales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ServiciosAdicionalesRepo extends JpaRepository<ServiciosAdicionales, Long> {
}
