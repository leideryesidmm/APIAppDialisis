package com.Dialisis.DialisisPeritoneal.persistence.repository;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Alergia;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Eps;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpsRepository extends JpaRepository<Eps, Integer> {

    public List<Eps> findAll();
}
