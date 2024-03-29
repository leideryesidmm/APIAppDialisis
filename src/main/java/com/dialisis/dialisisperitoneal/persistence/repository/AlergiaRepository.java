package com.dialisis.dialisisperitoneal.persistence.repository;

import com.dialisis.dialisisperitoneal.persistence.entity.Alergia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlergiaRepository extends JpaRepository<Alergia, Integer> {

    public List<Alergia> findAll();

    public Alergia findById(int id);

    @Modifying
    @Query(value = "update alergia set nombre=:nombre where id_alergia=:id_alergia", nativeQuery = true)
    public void actualizarAlergia(@Param("nombre")String nombre,
                                  @Param("id_alergia")int idAlergia);
}
