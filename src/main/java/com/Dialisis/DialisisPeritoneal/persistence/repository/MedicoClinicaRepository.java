package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.MedicoClinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicoClinicaRepository extends JpaRepository<MedicoClinica, Integer> {

    public List<MedicoClinica> findAll();

    public MedicoClinica findById(int id_medico_clinica);

    @Modifying
    @Query(value = "update medico_clinica set medico=:medico, clinica=:clinica, activa=:activa where id_medico_clinica=:id_medico_clinica and activa=true", nativeQuery = true)
    public void actualizarMedicoClinica(@Param("id_medico_clinica")int id_medico_clinica,
                                        @Param("medico")long medico,
                                        @Param("clinica")int clinica,
                                        @Param("activa")boolean activa);
    @Query(value = "select *  from clinica c join medico_clinica mc on c.id_clinica=mc.clinica join medico m on m.cedula=mc.medico where mc.medico=:cedula and mc.activa=true", nativeQuery = true)
    public List<MedicoClinica> findAllByMedico(long cedula);
    @Query(value = "select *  from clinica c join medico_clinica mc on c.id_clinica=mc.clinica join medico m on m.cedula=mc.medico where mc.medico=:cedula and mc.clinica=:id_clinica", nativeQuery = true)
    public MedicoClinica findClinicaPorMedico(@Param("cedula")long cedula,
                                              @Param("id_clinica")int id_clinica);
    @Modifying
    @Query(value = "update medico_clinica mc set activa=true where mc.medico=:cedula and mc.clinica=:id_clinica", nativeQuery = true)
    public void activarClinica(@Param("cedula")long cedula,
                               @Param("id_clinica")int id_clinica);
    @Modifying
    @Query(value = "update medico_clinica mc set activa=false where mc.medico=:cedula and mc.clinica=:id_clinica", nativeQuery = true)
    public void inactivarClinica (@Param("cedula")long cedula,
                                  @Param("id_clinica")int id_clinica);
    @Query(value = "select * from clinica c join medico_clinica mc on mc.clinica=c.id_clinica join medico m on m.cedula=mc.medico where mc.medico=:cedula and activa=false", nativeQuery = true)
    public List<MedicoClinica> findClinicasPasadas(@Param("cedula")long cedula);

}
