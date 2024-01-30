package com.dialisis.dialisisperitoneal.persistence.repository;

import com.dialisis.dialisisperitoneal.persistence.entity.ChequeoMensual;
import com.dialisis.dialisisperitoneal.persistence.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ChequeoMensualRepository extends JpaRepository<ChequeoMensual, Integer> {

    @Query(value = "SELECT * FROM chequeomensual where cita=:idCita",nativeQuery = true)
    public ChequeoMensual findUltimoChequeoMensual(int idCita);

    public ChequeoMensual findByCita(Cita cita);

}
