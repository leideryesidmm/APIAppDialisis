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
    @Query(value = "update programar_medicamento set fecha_ini=:fecha_ini, fecha_fin=:fecha_fin where id_programar_medicamento=:id_programar_medicamento", nativeQuery = true)
    public void actualizarProgramarMedicamento(@Param("id_programar_medicamento")int id_programar_medicamento,
                                               @Param("fecha_ini")Date fecha_ini,
                                               @Param("fecha_fin")Date fecha_fin);
}
