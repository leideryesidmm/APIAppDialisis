package com.dialisis.dialisisperitoneal.persistence.repository;

import com.dialisis.dialisisperitoneal.persistence.entity.ChequeoMensual;
import com.dialisis.dialisisperitoneal.persistence.entity.Cita;
import com.dialisis.dialisisperitoneal.persistence.entity.VisitaEspecialista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VisitaEspecialistaRepository extends JpaRepository<VisitaEspecialista, Integer> {

    @Query(value = "SELECT * FROM visita_especialista where cita=:idCita",nativeQuery = true)
    public VisitaEspecialista findUltimaVisita(int idCita);


        public VisitaEspecialista findByCita(Cita cita);
}

