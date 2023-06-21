package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Cita;
import com.Dialisis.DialisisPeritoneal.persistence.entity.FormulaMedicamento;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Paciente;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FormulaMedicamentoRepository extends JpaRepository<FormulaMedicamento, Integer> {

    public List<FormulaMedicamento> findAll();

    public List<FormulaMedicamento> findAllByPaciente(Paciente paciente);

    @Modifying
    @Query(value = "update formula_medicamento set medicamento=:medicamento, intervalo_tiempo=:intervalo_tiempo, tomas=:tomas, dosis=:dosis where id_formula_medicamento=:id_formula_medicamento", nativeQuery = true)
    public void actualizarFormula(@Param("id_formula_medicamento")int id_formula_medicamento,
                                  @Param("medicamento")int medicamento,
                                  @Param("intervalo_tiempo")int intervalo_tiempo,
                                  @Param("tomas")int tomas,
                                  @Param("dosis")int dosis);

}
