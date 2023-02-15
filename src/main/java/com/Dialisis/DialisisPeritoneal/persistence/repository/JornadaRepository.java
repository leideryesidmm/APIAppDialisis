package com.Dialisis.DialisisPeritoneal.persistence.repository;


import com.Dialisis.DialisisPeritoneal.persistence.entity.Jornada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JornadaRepository extends JpaRepository<Jornada,Integer> {
    public Jornada findById(int id_jornada);
}
