package co.edu.uniquindio.criterion.repositories;

import co.edu.uniquindio.criterion.model.DetalleCompraArticulo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleCompraArticuloRepo extends JpaRepository<DetalleCompraArticulo, Long> {
}
