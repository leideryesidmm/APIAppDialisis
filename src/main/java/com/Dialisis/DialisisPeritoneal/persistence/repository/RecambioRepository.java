package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.PrescripcionDia;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Recambio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecambioRepository  extends JpaRepository<Recambio, Integer> {

    public List<Recambio> findByPrescripcionDia(PrescripcionDia prescripcionDia);
}
