package co.edu.uniquindio.criterion.repositories;



import co.edu.uniquindio.criterion.model.Articulo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloRepo extends JpaRepository<Articulo, Long> {
}
