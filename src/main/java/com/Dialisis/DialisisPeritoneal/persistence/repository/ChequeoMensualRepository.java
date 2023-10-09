package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.ChequeoMensual;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Cita;
import com.Dialisis.DialisisPeritoneal.persistence.entity.PrescripcionDia;
import com.Dialisis.DialisisPeritoneal.persistence.entity.VisitaEspecialista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChequeoMensualRepository extends JpaRepository<ChequeoMensual, Integer> {

    @Query(value = "SELECT * FROM chequeomensual where cita=:idCita",nativeQuery = true)
    public List<ChequeoMensual> findUltimoChequeoMensual(int idCita);

    public ChequeoMensual findByCita(Cita cita);

}
