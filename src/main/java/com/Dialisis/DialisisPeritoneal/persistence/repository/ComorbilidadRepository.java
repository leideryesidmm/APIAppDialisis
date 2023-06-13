package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Comorbilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface ComorbilidadRepository extends JpaRepository<Comorbilidad,Integer> {

    public List<Comorbilidad> findAll();



    @Modifying
    @Query(value = "update comorbilidad set paciente=:paciente, enfermedad=:enfermedad where idComorbilidad=:idComorbilidad", nativeQuery = true)
    public void actualizarComorbilidad(@Param("idComorbilidad")int idComorbilidad,
                                       @Param("paciente")long paciente,
                                       @Param("enfermedad")int enfermedad);
    @Modifying
    @Query(value = "update comorbilidad set activo=false where idComorbilidad=:idComorbilidad", nativeQuery = true)
    public void inactivarComorbilidad(@Param("idComorbilidad")int idComorbilidad);

    @Modifying
    @Query(value = "update comorbilidad set activo=true where idComorbilidad=:idComorbilidad", nativeQuery = true)
    public void activarComorbilidad(@Param("idComorbilidad")int idComorbilidad);

    @GetMapping
    @Query(value = "Select * from comorbilidad where paciente=:paciente and activo=false", nativeQuery = true)
    public List<Comorbilidad> findAllActivo(String paciente);

}
