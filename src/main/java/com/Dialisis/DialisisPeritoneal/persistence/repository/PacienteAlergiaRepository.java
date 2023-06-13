package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.PacienteAlergia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PacienteAlergiaRepository extends JpaRepository<PacienteAlergia, Integer> {

    public List<PacienteAlergia> findAll();

    @Query(value = "select * from alergia a join pacienteAlergia pa on a.idAlergia=pa.alergia join paciente p on pa.paciente=p.cedula where p.cedula=:cedula and pa.activa=true", nativeQuery = true)
    public List<PacienteAlergia> findAllByPaciente(String cedula);

    @Modifying
    @Query(value = "update pacienteAlergia set paciente=:paciente, alergia=:alergia, activa=:activa where idPacienteAlergia=:idPacienteAlergia", nativeQuery = true)
    public void actualizarPacienteAlergia(@Param("idPacienteAlergia")int idPacienteAlergia,
                                          @Param("paciente")String paciente,
                                          @Param("alergia")int alergia,
                                          @Param("activa")boolean activa);

    @Query(value = "select * from alergia a join pacienteAlergia pa on a.idAlergia=pa.alergia join paciente p on p.cedula=pa.paciente where pa.paciente=:cedula and pa.alergia=:idAlergia", nativeQuery = true)
    public PacienteAlergia findAlergiaPaciente(@Param("cedula")String cedula,
                                               @Param("idAlergia")int idAlergia);
    @Modifying
    @Query(value = "update pacienteAlergia set activa=false where paciente=:cedula and alergia=:idAlergia", nativeQuery = true)
    public void inactivarAlergia(@Param("cedula")String cedula,
                                 @Param("idAlergia")int idAlergia);

    @Modifying
    @Query(value = "update pacienteAlergia set activa=true where paciente=:cedula and alergia=:idAlergia", nativeQuery = true)
    public void activarAlergia(@Param("cedula")String cedula,
                               @Param("idAlergia")int idAlergia);

    @Query(value = "select * from alergia a join pacienteAlergia pa on a.idAlergia=pa.alergia join paciente p on pa.paciente=p.cedula where pa.paciente=:cedula and activa=false", nativeQuery = true)
    public List<PacienteAlergia> findAlergiasPasadas(@Param("cedula")String cedula);

}
