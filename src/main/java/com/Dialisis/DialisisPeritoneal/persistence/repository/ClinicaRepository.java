package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ClinicaRepository extends JpaRepository<Clinica, Integer> {

    public List<Clinica> findAll();

    public Clinica findById(int id_clinica);
    @Modifying
    @Query(value = "update clinica set nombre=:nombre, direccion=:direccion where id_clinica=:id_clinica", nativeQuery = true)
    public void actualizarClinica(@Param("id_clinica") int id_clinica,
                                  @Param("nombre") String nombre,
                                  @Param("direccion") String direccion);
}
