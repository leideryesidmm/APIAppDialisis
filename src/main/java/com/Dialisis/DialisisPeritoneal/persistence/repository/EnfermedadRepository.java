package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Enfermedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnfermedadRepository extends JpaRepository<Enfermedad,Integer> {
    public Enfermedad findById(int id);
    @Modifying
    @Query(value = "Update enfermedad set nombre=:nombre where id_enfermedad=:id_enfermedad", nativeQuery = true)
    public void actualizarEnfermedad(@Param("id_enfermedad") long id_enfermedad, @Param("nombre") String nombre);


}
