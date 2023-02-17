package com.Dialisis.DialisisPeritoneal.service;


import com.Dialisis.DialisisPeritoneal.exceptions.ToDoExceptions;
import com.Dialisis.DialisisPeritoneal.mapper.JornadaInDtoToJornada;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Cormobilidad;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Jornada;
import com.Dialisis.DialisisPeritoneal.persistence.repository.JornadaRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.JornadaInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Jornada findById(int id_jornada){
        Optional<Jornada> optionalJornada = this.jornadaRepository.findById(id_jornada);
        if (optionalJornada.isEmpty()) {
            throw new ToDoExceptions("Cormobilidad no encontrada", HttpStatus.NOT_FOUND);
        }
        return optionalJornada.get();
    }
}
