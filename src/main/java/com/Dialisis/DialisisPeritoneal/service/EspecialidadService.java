package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.mapper.EspecialidaInDtoToEspecialidad;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Especialidad;
import com.Dialisis.DialisisPeritoneal.persistence.repository.EspecialidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadService {
    private final EspecialidadRepository repository;
    private final EspecialidaInDtoToEspecialidad mapper;

    public EspecialidadService(EspecialidadRepository repository, EspecialidaInDtoToEspecialidad mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    public List<Especialidad> findAllEspecialidad(){
        return this.repository.findAll();
    }

}
