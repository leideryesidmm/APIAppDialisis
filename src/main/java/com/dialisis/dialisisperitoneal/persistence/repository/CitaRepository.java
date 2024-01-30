package com.dialisis.dialisisperitoneal.persistence.repository;

import com.dialisis.dialisisperitoneal.persistence.entity.Cita;
import com.dialisis.dialisisperitoneal.persistence.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Integer> {

    public List<Cita> findAllByPaciente(Paciente paciente);
    @Query(value = "SELECT * FROM cita where paciente=:paciente and fecha <:hoy",nativeQuery = true)
    public List<Cita> findAllCitasAntiguasByPaciente(Paciente paciente, LocalDateTime hoy);
    @Query(value = "SELECT * FROM cita where paciente=:paciente and fecha >:hoy",nativeQuery = true)
    public List<Cita> findAllCitasFuturasByPaciente(Paciente paciente, LocalDateTime hoy);
    @Query(value = "SELECT * FROM cita where paciente=:paciente order by fecha DESC LIMIT 1 ",nativeQuery = true)
    public Cita findUltimaCita(Paciente paciente);
    @Modifying
    @Query(value = "update cita set fecha_fin=:fecha, finalizado=true where id_cita=:cita",nativeQuery = true)
    public void finalizar(@Param("fecha") LocalDateTime fecha,
                                @Param("cita") int cita);




    @Modifying
    @Query(value = "update cita set cedula_medico=:cedula_medico, cedula_paciente=:cedula_paciente, direccion=:direccion, hora=:hora, fecha=:fecha where id_cita=:id_cita",nativeQuery = true)
    public void actualizarCita(@Param("id_cita")int cita,
                               @Param("cedula_paciente")String cedulaPaciente,
                               @Param("direccion")String direccion,
                               @Param("hora")LocalDateTime hora,
                               @Param("fecha") LocalDateTime fecha);
}


