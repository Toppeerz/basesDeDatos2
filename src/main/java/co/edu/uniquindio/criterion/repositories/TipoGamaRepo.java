package co.edu.uniquindio.criterion.repositories;




import co.edu.uniquindio.criterion.model.TipoGama;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TipoGamaRepo extends JpaRepository<TipoGama, String> {
}
