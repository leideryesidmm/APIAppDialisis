package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Parentesco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParentescoRepository extends JpaRepository<Parentesco, Integer> {

    public List<Parentesco> findAll();

    public Parentesco findById(int id_parentesco);

    @Modifying
    @Query(value = "update parentesco set descripcion=:descripcion where id_parentesco=:id_parentesco", nativeQuery = true)
    public void actualizarParentesco(@Param("id_parentesco")int id_parentesco, @Param("descripcion")String descripcion);


}
