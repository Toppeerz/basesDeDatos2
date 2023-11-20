package co.edu.uniquindio.criterion.repositories;

import co.edu.uniquindio.criterion.model.Agencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenciaRepo extends JpaRepository<Agencia, Long> {
}
