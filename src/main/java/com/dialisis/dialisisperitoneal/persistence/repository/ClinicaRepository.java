package com.dialisis.dialisisperitoneal.persistence.repository;

import com.dialisis.dialisisperitoneal.persistence.entity.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ClinicaRepository extends JpaRepository<Clinica, Integer> {

    public List<Clinica> findAll();

    @Modifying
    @Query(value = "update clinica set nombre=:nombre, direccion=:direccion where id_clinica=:id_clinica", nativeQuery = true)
    public void actualizarClinica(@Param("id_clinica") int idClinica,
                                  @Param("nombre") String nombre,
                                  @Param("direccion") String direccion);
}
