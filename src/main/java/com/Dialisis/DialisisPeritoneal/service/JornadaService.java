package com.Dialisis.DialisisPeritoneal.service;


import com.Dialisis.DialisisPeritoneal.mapper.JornadaInDtoToJornada;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Jornada;
import com.Dialisis.DialisisPeritoneal.persistence.repository.JornadaRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.JornadaInDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JornadaService {
    private final JornadaRepository jornadaRepository;
    private final JornadaInDtoToJornada mapper;


    public JornadaService(JornadaRepository jornadaRepository, JornadaInDtoToJornada mapper) {
        this.jornadaRepository = jornadaRepository;
        this.mapper = mapper;
    }

    public Jornada crearJornada(JornadaInDto jornadaInDto){
        Jornada jornada = mapper.map(jornadaInDto);
        return this.jornadaRepository.save(jornada);
    }

    public List<Jornada> findAll(){
        return this.jornadaRepository.findAll();
    }
    public Jornada findById(int id){
        return this.jornadaRepository.findById(id);
    }
}
