package co.edu.uniquindio.criterion.repositories;



import co.edu.uniquindio.criterion.model.TipoArticulo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TipoArticuloRepo extends JpaRepository<TipoArticulo, Long> {
}
