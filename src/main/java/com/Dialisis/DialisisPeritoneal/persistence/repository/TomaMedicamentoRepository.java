package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.TomaMedicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface TomaMedicamentoRepository extends JpaRepository<TomaMedicamento, Integer> {

    public List<TomaMedicamento> findAll();



    @Modifying
    @Query(value = "update tomaMedicamento set hora=:hora, tomado=:tomado where idTipoMedicamento=:idTipoMedicamento", nativeQuery = true)
    public void actualizarTomaMedicamento(@Param("idTipoMedicamento")int idTipoMedicamento,
                                          @Param("hora") LocalDateTime hora,
                                          @Param("tomado")boolean tomado);
}
