package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, String> {


    public Paciente findByCedula(String cedula);

    @Modifying
    @Query(value="update paciente set  eps=:eps, altura=:altura, peso=:peso, pesoSeco=:pesoSeco, direccion=:direccion, ocupacion=:ocupacion where CEDULA=:cedula", nativeQuery = true)
    public void actualizarDatosPaciente(@Param("cedula")String cedula,
                                        @Param("eps")String eps,
                                        @Param("altura")int altura,
                                        @Param("peso") double peso,
                                        @Param("pesoSeco") double pesoSeco,
                                        @Param("direccion")String direccion,
                                        @Param("ocupacion")String ocupacion );
}
