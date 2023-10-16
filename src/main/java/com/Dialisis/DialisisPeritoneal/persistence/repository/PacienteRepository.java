package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.CuidadorPaciente;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, String> {

    public Paciente findByCedula(String cedula);

    @Query(value = "SELECT * from paciente where activo = true", nativeQuery = true)
    public List<Paciente> findPacientesActivos();

    @Query(value = "SELECT * from paciente where activo = false", nativeQuery = true)
    public List<Paciente> findPacientesInactivos(String cedula);


}


