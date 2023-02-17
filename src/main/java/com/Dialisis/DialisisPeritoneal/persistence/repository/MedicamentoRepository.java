package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {

    @Modifying
    @Query(value = "update medicamento set nombre=:nombre, concentracion=:concentracion, via_administracion=:via_administracion, descripcion=:descripcion where id_medicamento=:id_medicamento", nativeQuery = true)
    public void actualizarMedicamento(@Param("nombre")String nombre,
                                      @Param("concentracion") int concentracion,
                                      @Param("via_administracion") int via_administracion,
                                      @Param("descripcion") String descripcion,
                                      @Param("id_medicamento")int id_medicamento);

}
