package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Parentesco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParentescoRepository extends JpaRepository<Parentesco, Integer> {

    public List<Parentesco> findAll();



    @Modifying
    @Query(value = "update parentesco set descripcion=:descripcion where idParentesco=:idParentesco", nativeQuery = true)
    public void actualizarParentesco(@Param("idParentesco")int idParentesco, @Param("descripcion")String descripcion);


}
