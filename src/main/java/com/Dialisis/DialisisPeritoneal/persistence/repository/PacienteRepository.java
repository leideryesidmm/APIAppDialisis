package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {


    public Paciente findById(long cedula);
    @Modifying
    @Query(value="update paciente set  eps=:eps, altura=:altura, peso=:peso, peso_seco=:peso_seco, direccion=:direccion, ocupacion=:ocupacion where CEDULA=:cedula", nativeQuery = true)
    public void actualizarDatosPaciente(@Param("cedula")long cedula,
                                        @Param("eps")String eps,
                                        @Param("altura")int altura,
                                        @Param("peso") double peso,
                                        @Param("peso_seco") double peso_seco,
                                        @Param("direccion")String direccion,
                                        @Param("ocupacion")String ocupacion );
}
