package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Cita;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Recambio;
import com.Dialisis.DialisisPeritoneal.persistence.entity.VisitaEspecialista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VisitaEspecialistaRepository extends JpaRepository<VisitaEspecialista, Integer> {

    @Query(value = "SELECT * FROM visita_especialista where cita=:idCita",nativeQuery = true)
    public List<VisitaEspecialista> findUltimaVisita(int idCita);

        public VisitaEspecialista findByCita(Cita cita);
}

