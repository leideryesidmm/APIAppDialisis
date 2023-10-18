package com.dialisis.dialisisperitoneal.persistence.repository;

import com.dialisis.dialisisperitoneal.persistence.entity.FormulaMedicamento;
import com.dialisis.dialisisperitoneal.persistence.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FormulaMedicamentoRepository extends JpaRepository<FormulaMedicamento, Integer> {

    public List<FormulaMedicamento> findAll();

    public List<FormulaMedicamento> findAllByPaciente(Paciente paciente);

    @Modifying
    @Query(value = "update formula_medicamento set intervalo_tiempo=:intervalo_tiempo, tomas=:tomas, dosis=:dosis where id_formula_medicamento=:id_formula_medicamento", nativeQuery = true)
    public void actualizarFormula(@Param("id_formula_medicamento")int idFormulaMedicamento,
                                  @Param("intervalo_tiempo")int intervaloTiempo,
                                  @Param("tomas")int tomas,
                                  @Param("dosis")int dosis);

}
