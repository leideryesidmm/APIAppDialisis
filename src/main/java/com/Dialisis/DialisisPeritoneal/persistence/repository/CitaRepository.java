package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Cita;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Integer> {

    public List<Cita> findAllByPaciente(Paciente paciente);
    @Query(value = "SELECT * FROM cita where paciente=:paciente and fecha <:hoy",nativeQuery = true)
    public List<Cita> findAllCitasAntiguasByPaciente(Paciente paciente, LocalDateTime hoy);
    @Query(value = "SELECT * FROM cita where paciente=:paciente and fecha >:hoy",nativeQuery = true)
    public List<Cita> findAllCitasFuturasByPaciente(Paciente paciente, LocalDateTime hoy);



    @Modifying
    @Query(value = "update cita set nombre_medico=:nombre_medico, paciente=:paciente, especialidad_medico=:especialidad_medico, lugar=:lugar, direccion=:direccion, fecha=:fecha where id_cita=:id_cita",nativeQuery = true)
    public void actualizarCita(@Param("id_cita")int cita,
                               @Param("nombre_medico") String nombre_medico,
                               @Param("paciente")long paciente,
                               @Param("especialidad_medico") int especialidad_medico,
                               @Param("lugar")String lugar,
                               @Param("direccion")String direccion,
                               @Param("fecha") LocalDateTime fecha);
}


