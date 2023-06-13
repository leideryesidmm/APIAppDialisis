package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Enfermedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnfermedadRepository extends JpaRepository<Enfermedad,Integer> {
    @Modifying
    @Query(value = "Update enfermedad set nombre=:nombre where idEnfermedad=:idEnfermedad", nativeQuery = true)
    public void actualizarEnfermedad(@Param("idEnfermedad") long idEnfermedad, @Param("nombre") String nombre);


}
