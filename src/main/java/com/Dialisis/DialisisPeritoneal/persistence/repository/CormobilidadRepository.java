package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Cormobilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface CormobilidadRepository extends JpaRepository<Cormobilidad,Integer> {

    public List<Cormobilidad> findAll();

    public Cormobilidad findById(int id_cormobilidad);

    @Modifying
    @Query(value = "update cormobilidad set paciente=:paciente, enfermedad=:enfermedad where id_cormobilidad=:id_cormobilidad", nativeQuery = true)
    public void actualizarCormobilidad(@Param("id_cormobilidad")int id_cormobilidad,
                                       @Param("paciente")long paciente,
                                       @Param("enfermedad")int enfermedad);
    @Modifying
    @Query(value = "update cormobilidad set activo=false where id_cormobilidad=:id_cormobilidad", nativeQuery = true)
    public void inactivarCormobilidad(@Param("id_cormobilidad")int id_cormobilidad);

    @Modifying
    @Query(value = "update cormobilidad set activo=true where id_cormobilidad=:id_cormobilidad", nativeQuery = true)
    public void activarCormobilidad(@Param("id_cormobilidad")int id_cormobilidad);

    @GetMapping
    @Query(value = "Select * from cormobilidad where paciente=:paciente and activo=false", nativeQuery = true)
    public List<Cormobilidad> findAllActivo(long paciente);

}
