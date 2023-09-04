package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface PrescripcionDiaRepository extends JpaRepository<PrescripcionDia, Integer> {

    @Modifying
    @Query(value = "update prescripcion set orificioSalida=:orificioSalida, nocheSeca=:nocheSeca where id_prescripcion=:id_prescripcion", nativeQuery = true)
    public void actualizarPrescripcion(@Param("orificioSalida") String orificioSalida,
                                       @Param("nocheSeca") boolean nocheSeca,
                                       @Param("id_prescripcion") int id_prescripcion);

    public List<PrescripcionDia> findByCita(Cita cita);

    public PrescripcionDia findByIdPrescripcionDia(int id);

}

