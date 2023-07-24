package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.mapper.PrescripcionInDtoToPrescripcion;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Cita;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Prescripcion;
import com.Dialisis.DialisisPeritoneal.persistence.entity.PrescripcionDia;
import com.Dialisis.DialisisPeritoneal.persistence.repository.PrescripcionDiaRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.PrescripcionInDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PrescripcionDiaService {

    private final PrescripcionDiaRepository repository;
    private final PrescripcionInDtoToPrescripcion mapper;

    public PrescripcionDiaService(PrescripcionDiaRepository repository, PrescripcionInDtoToPrescripcion mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }



    /*public PrescripcionDia createPrescripcion(PrescripcionInDto prescripcionInDto) {
        Prescripcion pres = mapper.map(prescripcionInDto);
        return this.repository.save(pres);
    }*/

    public List<PrescripcionDia> findAllPrescripciones() {
        return this.repository.findAll();
    }

    public List<PrescripcionDia> findByCita(Cita cita) {
        return this.repository.findByCita(cita);
    }



    @Transactional
    public void actualizarPrescripcion(PrescripcionInDto prescripcionInDto, boolean nocheSeca, int idPrescripcion){
        this.repository.actualizarPrescripcion(prescripcionInDto.getOrificioSalida(), nocheSeca, idPrescripcion);
    }



}
