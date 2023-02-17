package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.TipoRecambio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TipoRecambioRepository extends JpaRepository<TipoRecambio, Integer> {

    public List<TipoRecambio> findAll();



    @Modifying
    @Query(value = "update tipo_recambio set descripcion=:descripcion where id_tipo=:id_tipo", nativeQuery = true)
    public void actualizarTipoRecambio(@Param("id_tipo")int id_tipo,
                                       @Param("descripcion") String descripcion);
}
