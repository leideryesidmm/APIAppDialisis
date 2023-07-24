package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.exceptions.ToDoExceptions;
import com.Dialisis.DialisisPeritoneal.mapper.PrescripcionInDtoToPrescripcion;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Cita;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Medico;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Prescripcion;
import com.Dialisis.DialisisPeritoneal.persistence.repository.PrescripcionRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.CitaInDto;
import com.Dialisis.DialisisPeritoneal.service.dto.PrescripcionInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PrescripcionService {

    private final PrescripcionRepository repository;
    private final PrescripcionInDtoToPrescripcion mapper;

    public PrescripcionService(PrescripcionRepository repository, PrescripcionInDtoToPrescripcion mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }



    public Prescripcion createPrescripcion(PrescripcionInDto prescripcionInDto) {
        Prescripcion pres = mapper.map(prescripcionInDto);
        return this.repository.save(pres);
    }

    public List<Prescripcion> findAllPrescripciones() {
        return this.repository.findAll();
    }



    @Transactional
    public void actualizarPrescripcion(PrescripcionInDto prescripcionInDto, boolean nocheSeca, int idPrescripcion){
        this.repository.actualizarPrescripcion(prescripcionInDto.getOrificioSalida(), nocheSeca, idPrescripcion);
    }



}
