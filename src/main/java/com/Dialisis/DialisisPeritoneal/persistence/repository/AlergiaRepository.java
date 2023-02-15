package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Alergia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlergiaRepository extends JpaRepository<Alergia, Integer> {

    public List<Alergia> findAll();


    @Modifying
    @Query(value = "update alergia set nombre=:nombre where id_alergia=:id_alergia", nativeQuery = true)
    public void actualizarAlergia(@Param("nombre")String nombre,
                                  @Param("id_alergia")int id_alergia);
}
