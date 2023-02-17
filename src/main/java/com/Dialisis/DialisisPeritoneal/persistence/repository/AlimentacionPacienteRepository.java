package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.AlimentacionPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface AlimentacionPacienteRepository extends JpaRepository<AlimentacionPaciente, Integer> {

    public List<AlimentacionPaciente> findAll();



    @Query(value = "select * from alimentacion_paciente ap join paciente p on p.cedula=ap.paciente where ap.paciente=:cedula", nativeQuery = true)
    public List<AlimentacionPaciente> findAllByPaciente(@Param("cedula") long cedula);

    @Query(value = "select * from alimentacion_paciente ap join paciente p on p.cedula=ap.paciente where ap.paciente=:cedula and ap.fecha_hora between :fecha1 and :fecha2", nativeQuery = true)
    public List<AlimentacionPaciente> findAllByPacienteByRango(@Param("cedula") long cedula,
                                                               @Param("fecha1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime fecha1,
                                                               @Param("fecha2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime fecha2);
    @Modifying
    @Query(value = "update alimentacion_paciente set alimentacion=:alimentacion, jornada=:jornada, fecha_hora=:fecha_hora, cantidad=:cantidad where id_alimentacion_paciente=:id_alimentacion_paciente and paciente=:cedula", nativeQuery = true)
    public void actualizarAlimentacionPaciente(@Param("id_alimentacion_paciente") int id_alimentacion_paciente,
                                               @Param("jornada") int jornada,
                                               @Param("fecha_hora")LocalDateTime fecha_hora,
                                               @Param("cantidad") int cantidad,
                                               @Param("cedula") long cedula,
                                               @Param("alimentacion")String alimentacion);

}
