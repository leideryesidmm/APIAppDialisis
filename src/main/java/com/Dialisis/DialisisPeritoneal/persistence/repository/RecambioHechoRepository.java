package com.Dialisis.DialisisPeritoneal.persistence.repository;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Recambio;
import com.Dialisis.DialisisPeritoneal.persistence.entity.RecambioHecho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface RecambioHechoRepository extends JpaRepository<RecambioHecho, Integer> {
    List<RecambioHecho> findByRecambio(Recambio recambio);
    @Query(value = "Select * from recambio_hecho where recambio=:recambio and Date(fecha)=:fecha", nativeQuery = true)
    public RecambioHecho findByRecambioAndFecha(@Param("recambio") int id_recambio,
                                                @Param("fecha") LocalDate fecha);
}
