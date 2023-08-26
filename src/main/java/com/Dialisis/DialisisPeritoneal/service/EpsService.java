package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.exceptions.ExceptionsEntitys;
import com.Dialisis.DialisisPeritoneal.mapper.AlergiaInDtoToAlergia;
import com.Dialisis.DialisisPeritoneal.mapper.EpsInDtoToEps;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Alergia;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Eps;
import com.Dialisis.DialisisPeritoneal.persistence.repository.AlergiaRepository;
import com.Dialisis.DialisisPeritoneal.persistence.repository.EpsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpsService {
    private final EpsRepository repository;
    private final EpsInDtoToEps mapper;

    public EpsService(EpsRepository repositorio, EpsInDtoToEps mapper) {
        this.repository = repositorio;
        this.mapper = mapper;
    }

    public List<Eps> findAllEps(){

        return this.repository.findAll();
    }
}
