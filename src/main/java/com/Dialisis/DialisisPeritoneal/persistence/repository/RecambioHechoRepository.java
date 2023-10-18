package com.dialisis.dialisisperitoneal.persistence.repository;
import com.dialisis.dialisisperitoneal.persistence.entity.Recambio;
import com.dialisis.dialisisperitoneal.persistence.entity.RecambioHecho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RecambioHechoRepository extends JpaRepository<RecambioHecho, Integer> {
    List<RecambioHecho> findByRecambio(Recambio recambio);
    @Query(value = "Select * from recambio_hecho where recambio=:recambio and Date(fecha_real)=:fecha", nativeQuery = true)
    public RecambioHecho findByRecambioAndFecha(@Param("recambio") int idRecambio,
                                                @Param("fecha") LocalDate fecha);

    public RecambioHecho findById(int idRecambioHecho);
}
