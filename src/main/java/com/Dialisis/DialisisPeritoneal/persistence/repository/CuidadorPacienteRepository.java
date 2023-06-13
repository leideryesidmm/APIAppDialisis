package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.CuidadorPaciente;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface CuidadorPacienteRepository extends JpaRepository<CuidadorPaciente,Integer> {

    @Modifying
    @Query(value = "Update cuidadorPaciente set activo=false, fechaFin=:fechaFin where idCuidadorPaciente=:idCuidadorPaciente", nativeQuery = true)
    public void inactivarCuidador(@Param("idCuidadorPaciente") long idCuidadorPaciente,
                                  @Param("fechaFin") LocalDate fechaFin);

    @Modifying
    @Query(value = "update cuidadorPaciente set fechaInicio=:fechaInicio,fechaFin=:fechaFin, activo=:activo where idCuidadorPaciente=:idCuidadorPaciente", nativeQuery = true)
    public void actualizarCuidadorPaciente(@Param("idCuidadorPaciente")int idCuidadorPaciente,
                                           @Param("fechaInicio") Date fechaInicio,
                                           @Param("fechaFin")Date fechaFin,
                                           @Param("activo")boolean activo);
    public List<CuidadorPaciente> findAllByPaciente(Paciente cedula);
    @Query(value = "SELECT * from cuidador c join cuidadorPaciente cp on c.cedula=cp.cuidador join parentesco p on c.parentesco=p.idParentesco where cp.paciente=:cedula and cp.activo=true", nativeQuery = true)
    public CuidadorPaciente findCuidadorActivo(String cedula);
}
