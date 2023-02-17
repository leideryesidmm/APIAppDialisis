package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.TomaMedicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TomaMedicamentoRepository extends JpaRepository<TomaMedicamento, Integer> {

    public List<TomaMedicamento> findAll();



    @Modifying
    @Query(value = "update toma_medicamento set hora=:hora, tomado=:tomado where id_tipo_medicamento=:id_tipo_medicamento", nativeQuery = true)
    public void actualizarTomaMedicamento(@Param("id_tipo_medicamento")int id_tipo_medicamento,
                                          @Param("hora") Date hora,
                                          @Param("tomado")boolean tomado);
}
