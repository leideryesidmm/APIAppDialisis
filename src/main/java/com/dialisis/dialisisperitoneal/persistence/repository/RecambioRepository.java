package com.dialisis.dialisisperitoneal.persistence.repository;

import com.dialisis.dialisisperitoneal.persistence.entity.PrescripcionDia;
import com.dialisis.dialisisperitoneal.persistence.entity.Recambio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecambioRepository  extends JpaRepository<Recambio, Integer> {

    public List<Recambio> findByPrescripcionDia(PrescripcionDia prescripcionDia);



}
