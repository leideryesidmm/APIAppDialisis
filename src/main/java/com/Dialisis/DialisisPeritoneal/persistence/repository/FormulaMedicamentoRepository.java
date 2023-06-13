package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Cita;
import com.Dialisis.DialisisPeritoneal.persistence.entity.FormulaMedicamento;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FormulaMedicamentoRepository extends JpaRepository<FormulaMedicamento, Integer> {

    public List<FormulaMedicamento> findAll();

    public List<FormulaMedicamento> findAllByCita(Cita cita);

    @Modifying
    @Query(value = "update formulaMedicamento set medicamento=:medicamento, intervaloTiempo=:intervaloTiempo, tomas=:tomas, dosis=:dosis where idFormulaMedicamento=:idFormulaMedicamento", nativeQuery = true)
    public void actualizarFormula(@Param("idFormulaMedicamento")int idFormulaMedicamento,
                                  @Param("medicamento")int medicamento,
                                  @Param("intervaloTiempo")int intervaloTiempo,
                                  @Param("tomas")int tomas,
                                  @Param("dosis")int dosis);

}
