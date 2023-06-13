package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.MedicoClinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicoClinicaRepository extends JpaRepository<MedicoClinica, Integer> {

    public List<MedicoClinica> findAll();

    @Modifying
    @Query(value = "update medicoClinica set medico=:medico, clinica=:clinica, activa=:activa where idMedicoClinica=:idMedicoClinica and activa=true", nativeQuery = true)
    public void actualizarMedicoClinica(@Param("idMedicoClinica")int idMedicoClinica,
                                        @Param("medico")String medico,
                                        @Param("clinica")int clinica,
                                        @Param("activa")boolean activa);
    @Query(value = "select *  from clinica c join medicoClinica mc on c.idClinica=mc.clinica join medico m on m.cedula=mc.medico where mc.medico=:cedula and mc.activa=true", nativeQuery = true)
    public List<MedicoClinica> findAllByMedico(String cedula);
    @Query(value = "select *  from clinica c join medicoClinica mc on c.idClinica=mc.clinica join medico m on m.cedula=mc.medico where mc.medico=:cedula and mc.clinica=:idClinica", nativeQuery = true)
    public MedicoClinica findClinicaPorMedico(@Param("cedula")String cedula,
                                              @Param("idClinica")int idClinica);
    @Modifying
    @Query(value = "update medicoClinica mc set activa=true where mc.medico=:cedula and mc.clinica=:idClinica", nativeQuery = true)
    public void activarClinica(@Param("cedula")String cedula,
                               @Param("idClinica")int idClinica);
    @Modifying
    @Query(value = "update medicoClinica mc set activa=false where mc.medico=:cedula and mc.clinica=:idClinica", nativeQuery = true)
    public void inactivarClinica (@Param("cedula")String cedula,
                                  @Param("idClinica")int idClinica);
    @Query(value = "select * from clinica c join medicoClinica mc on mc.clinica=c.idClinica join medico m on m.cedula=mc.medico where mc.medico=:cedula and activa=false", nativeQuery = true)
    public List<MedicoClinica> findClinicasPasadas(@Param("cedula")String cedula);

}
