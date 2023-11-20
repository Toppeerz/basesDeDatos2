package co.edu.uniquindio.criterion.repositories;


import co.edu.uniquindio.criterion.model.CompraArticulo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraArticuloRepo extends JpaRepository<CompraArticulo, Long> {
}
