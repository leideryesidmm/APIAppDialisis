package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.mapper.RecambioHechoInDtoToRecambioHecho;
import com.Dialisis.DialisisPeritoneal.mapper.VisitaEspecialistaInDtoToVisitaEspecialista;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Recambio;
import com.Dialisis.DialisisPeritoneal.persistence.entity.RecambioHecho;
import com.Dialisis.DialisisPeritoneal.persistence.entity.VisitaEspecialista;
import com.Dialisis.DialisisPeritoneal.persistence.repository.RecambioHechoRepository;
import com.Dialisis.DialisisPeritoneal.persistence.repository.VisitaEspecialistaRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.RecambioHechoInDto;
import com.Dialisis.DialisisPeritoneal.service.dto.VisitaEspecialistaInDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitaEspecialistaService {

    private final VisitaEspecialistaRepository repository;
    private final VisitaEspecialistaInDtoToVisitaEspecialista mapper;

    public VisitaEspecialistaService(VisitaEspecialistaRepository repository, VisitaEspecialistaInDtoToVisitaEspecialista mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public VisitaEspecialista crearVisita(VisitaEspecialistaInDto visitaEspecialistaInDto) {
        VisitaEspecialista visitaEspecialista = mapper.map(visitaEspecialistaInDto);
        return this.repository.save(visitaEspecialista);
    }

    public VisitaEspecialista findUltimaVisita(int idCita) {
        System.out.println(idCita);
        return this.repository.findUltimaVisita(idCita).get(0);
    }
}

