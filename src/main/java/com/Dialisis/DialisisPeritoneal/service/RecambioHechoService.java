package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.mapper.RecambioHechoInDtoToRecambioHecho;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Paciente;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Recambio;
import com.Dialisis.DialisisPeritoneal.persistence.entity.RecambioHecho;
import com.Dialisis.DialisisPeritoneal.persistence.repository.RecambioHechoRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.PacienteInDto;
import com.Dialisis.DialisisPeritoneal.service.dto.RecambioHechoInDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecambioHechoService {

    private final RecambioHechoRepository repository;
    private final RecambioHechoInDtoToRecambioHecho mapper;

    public RecambioHechoService(RecambioHechoRepository repository, RecambioHechoInDtoToRecambioHecho mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public RecambioHecho crearRecambio(RecambioHechoInDto recambioHechoInDto){
        RecambioHecho recambioHecho= mapper.map(recambioHechoInDto);
        return this.repository.save(recambioHecho);
    }
    public List<RecambioHecho> findByRecambio(Recambio recambio){
        return this.repository.findByRecambio(recambio);
    }
}