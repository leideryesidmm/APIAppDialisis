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
    @Query(value = "Update cuidador_paciente set activo=false, fecha_fin=:fecha_fin where id_cuidador_paciente=:id_cuidador_paciente", nativeQuery = true)
    public void inactivarCuidador(@Param("id_cuidador_paciente") long id_cuidador_paciente,
                                  @Param("fecha_fin") LocalDate fecha_fin);

    @Modifying
    @Query(value = "update cuidador_paciente set fecha_ini=:fecha_ini,fecha_fin=:fecha_fin, activo=:activo where id_cuidador_paciente=:id_cuidador_paciente", nativeQuery = true)
    public void actualizarCuidadorPaciente(@Param("id_cuidador_paciente")int id_cuidador_paciente,
                                           @Param("fecha_ini") Date fecha_ini,
                                           @Param("fecha_fin")Date fecha_fin,
                                           @Param("activo")boolean activo);
    public List<CuidadorPaciente> findAllByPaciente(Paciente cedula);
    @Query(value = "SELECT * from cuidador c join cuidador_paciente cp on c.cedula=cp.cuidador join parentesco p on c.parentesco=p.id_parentesco where cp.paciente=:cedula and cp.activo=true", nativeQuery = true)
    public CuidadorPaciente findCuidadorActivo(String cedula);
}
