package com.Dialisis.DialisisPeritoneal.persistence.repository;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Recambio;
import com.Dialisis.DialisisPeritoneal.persistence.entity.RecambioHecho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecambioHechoRepository extends JpaRepository<RecambioHecho, Integer> {
    List<RecambioHecho> findByRecambio(Recambio recambio);
}
