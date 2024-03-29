package com.dialisis.dialisisperitoneal.persistence.repository;

import com.dialisis.dialisisperitoneal.persistence.entity.PacienteAlergia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PacienteAlergiaRepository extends JpaRepository<PacienteAlergia, Integer> {

    public List<PacienteAlergia> findAll();

    @Query(value = "select * from alergia a join paciente_alergia pa on a.id_alergia=pa.alergia join paciente p on pa.paciente=p.cedula where p.cedula=:cedula and pa.activo=true", nativeQuery = true)
    public List<PacienteAlergia> findAllByPaciente(String cedula);

    @Modifying
    @Query(value = "update paciente_alergia set paciente=:paciente, alergia=:alergia, activo=:activo where id_paciente_alergia=:id_paciente_alergia", nativeQuery = true)
    public void actualizarPacienteAlergia(@Param("id_paciente_alergia")int idPacienteAlergia,
                                          @Param("paciente")String paciente,
                                          @Param("alergia")int alergia,
                                          @Param("activo")boolean activo);

    @Query(value = "select * from alergia a join paciente_alergia pa on a.id_alergia=pa.alergia join paciente p on p.cedula=pa.paciente where pa.paciente=:cedula and pa.alergia=:id_alergia", nativeQuery = true)
    public PacienteAlergia findAlergiaPaciente(@Param("cedula")String cedula,
                                               @Param("id_alergia")int idAlergia);
    @Modifying
    @Query(value = "update paciente_alergia set activo=false where paciente=:cedula and alergia=:id_alergia", nativeQuery = true)
    public void inactivarAlergia(@Param("cedula")String cedula,
                                 @Param("id_alergia")int idAlergia);

    @Modifying
    @Query(value = "update paciente_alergia set activo=true where paciente=:cedula and alergia=:id_alergia", nativeQuery = true)
    public void activarAlergia(@Param("cedula")String cedula,
                               @Param("id_alergia")int idAlergia);

    @Query(value = "select * from alergia a join paciente_alergia pa on a.id_alergia=pa.alergia join paciente p on pa.paciente=p.cedula where pa.paciente=:cedula and activo=false", nativeQuery = true)
    public List<PacienteAlergia> findAlergiasPasadas(@Param("cedula")String cedula);

}
