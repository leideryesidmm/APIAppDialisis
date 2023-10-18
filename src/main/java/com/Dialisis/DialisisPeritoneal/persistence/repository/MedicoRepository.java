package com.dialisis.dialisisperitoneal.persistence.repository;

import com.dialisis.dialisisperitoneal.persistence.entity.Medico;

import org.springframework.data.jpa.repository.JpaRepository;
public interface MedicoRepository extends JpaRepository<Medico, String>{
    public Medico findAllByCedula(String cedula);


}
