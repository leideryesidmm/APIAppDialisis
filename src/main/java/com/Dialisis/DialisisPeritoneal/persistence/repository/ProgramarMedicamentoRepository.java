package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.ProgramarMedicamento;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ProgramarMedicamentoRepository extends JpaRepository<ProgramarMedicamento, Integer> {

    public List<ProgramarMedicamento> findAll();



    @Modifying
    @Query(value = "update programar_medicamento set fechaInicio=:fechaInicio, fechaFin=:fechaFin where idProgramarMedicamento=:idProgramarMedicamento", nativeQuery = true)
    public void actualizarProgramarMedicamento(@Param("idProgramarMedicamento")int idProgramarMedicamento,
                                               @Param("fechaInicio")Date fechaInicio,
                                               @Param("fechaFin")Date fechaFin);
}
