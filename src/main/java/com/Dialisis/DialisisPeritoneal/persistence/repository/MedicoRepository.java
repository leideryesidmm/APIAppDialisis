package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Medico;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MedicoRepository extends JpaRepository<Medico, String>{
    public Medico findAllByCedula(String cedula);

    @Modifying
    @Query(value = "Update medico set activo=false where cedula=:cedula", nativeQuery = true)
    public void inactivarMedico(@Param("cedula") String cedula);


    @Modifying
    @Query(value = "Update medico set activo=true where cedula=:cedula", nativeQuery = true)
    public void activarMedico(@Param("cedula") String cedula);
}
