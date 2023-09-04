package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.exceptions.ToDoExceptions;
import com.Dialisis.DialisisPeritoneal.mapper.PrescripcionDiaToPrescripcionDia;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Cita;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Paciente;
import com.Dialisis.DialisisPeritoneal.persistence.entity.PrescripcionDia;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Recambio;
import com.Dialisis.DialisisPeritoneal.persistence.repository.PrescripcionDiaRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.PrescripcionDiaInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PrescripcionDiaService {

    private final PrescripcionDiaRepository repository;
    private final PrescripcionDiaToPrescripcionDia mapper;

    public PrescripcionDia findById( int id){
        return this.repository.findByIdPrescripcionDia(id);
    }

    public PrescripcionDiaService(PrescripcionDiaRepository repository, PrescripcionDiaToPrescripcionDia mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }



    /*public PrescripcionDia createPrescripcion(PrescripcionInDto prescripcionInDto) {
        Prescripcion pres = mapper.map(prescripcionInDto);
        return this.repository.save(pres);
    }*/

    public PrescripcionDia crearPrescripcionDia(PrescripcionDiaInDto prescripcionDiaInDto){
        PrescripcionDia prescripcionDia= mapper.map(prescripcionDiaInDto);
        return this.repository.save(prescripcionDia);
    }

    public List<PrescripcionDia> findAllPrescripciones() {
        return this.repository.findAll();
    }

    public List<PrescripcionDia> findByCita(Cita cita) {
        return this.repository.findByCita(cita);
    }



   /* @Transactional
    public void actualizarPrescripcion(PrescripcionInDto prescripcionInDto, boolean nocheSeca, int idPrescripcion){
        this.repository.actualizarPrescripcion(prescripcionInDto.getOrificioSalida(), nocheSeca, idPrescripcion);
    }*/

    public void deleteById(int id_prescripcionDia){
            this.repository.deleteById(id_prescripcionDia);

    }

}
