package co.edu.uniquindio.criterion.repositories;


import co.edu.uniquindio.criterion.model.CompraPaquete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraPaqueteRepo extends JpaRepository<CompraPaquete, Long> {
}
