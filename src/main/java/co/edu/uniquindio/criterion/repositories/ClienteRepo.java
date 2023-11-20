package co.edu.uniquindio.criterion.repositories;


import co.edu.uniquindio.criterion.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, String> {

    Cliente findByReservaHoteles_CompraReservas_Habitacion_Id(Long id);
}
