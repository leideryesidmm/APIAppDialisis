package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {

    @Modifying
    @Query(value = "update medicamento set nombre=:nombre, concentracion=:concentracion, viaAdministracion=:viaAdministracion, descripcion=:descripcion where idMedicamento=:idMedicamento", nativeQuery = true)
    public void actualizarMedicamento(@Param("nombre")String nombre,
                                      @Param("concentracion") int concentracion,
                                      @Param("viaAdministracion") int viaAdministracion,
                                      @Param("descripcion") String descripcion,
                                      @Param("idMedicamento")int idMedicamento);

}
