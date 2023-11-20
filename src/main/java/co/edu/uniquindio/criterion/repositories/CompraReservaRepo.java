package co.edu.uniquindio.criterion.repositories;


import co.edu.uniquindio.criterion.model.CompraReserva;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CompraReservaRepo extends JpaRepository<CompraReserva, Long> {
    
    @Transactional
    void deleteAllByReservaHotel_Id(Long id);

}
