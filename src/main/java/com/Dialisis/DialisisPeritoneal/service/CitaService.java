package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.exceptions.ToDoExceptions;
import com.Dialisis.DialisisPeritoneal.mapper.CitaInDtoToCita;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Cita;
import com.Dialisis.DialisisPeritoneal.persistence.entity.FormulaMedicamento;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Paciente;
import com.Dialisis.DialisisPeritoneal.persistence.repository.CitaRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.CitaInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.transaction.Transactional;
import java.time.*;
import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    private final CitaRepository repository;
    private final CitaInDtoToCita mapper;


    public CitaService(CitaRepository repository, CitaInDtoToCita mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Cita crearCita(CitaInDto citaInDto) {

        LocalDateTime now = LocalDateTime.now();
            Cita cita = mapper.map(citaInDto);

            return this.repository.save(cita);
    }

    public List<Cita> findAllByPaciente(Paciente cedula){
        return this.repository.findAllByPaciente(cedula);
    }
    public Cita findById(int idCita){
        Optional<Cita> optionalCita = this.repository.findById(idCita);
        if (optionalCita.isEmpty()) {
            throw new ToDoExceptions("Cita no encontrada", HttpStatus.NOT_FOUND);
        }
        return optionalCita.get();
    }
    @Transactional
    public void actualizarCita(int id_cita,CitaInDto citaInDto){
    }
    public void deleteById(int id_cita){
        Optional<Cita> optionalCita = this.repository.findById(id_cita);
        if (optionalCita.isEmpty()) {
            throw new ToDoExceptions("Cita no encontrada", HttpStatus.NOT_FOUND);
        }else {
            this.repository.deleteById(id_cita);
        }

    }


    public List<Cita> findAllCitasAntiguasByPaciente(Paciente cedula){
        LocalDateTime hoy=LocalDateTime.now();
        return this.repository.findAllCitasAntiguasByPaciente(cedula,hoy);
    }
    public List<Cita> findAllCitas(){
        return this.repository.findAll();
    }
    public List<Cita> findAllCitasFuturasByPaciente(Paciente cedula){
        LocalDateTime hoy=LocalDateTime.now();
        return this.repository.findAllCitasFuturasByPaciente(cedula,hoy);
    }


    public Cita findUltimaCita(Paciente paciente) {
        List<Cita> citas=this.repository.findUltimaCita(paciente);
            if(citas.isEmpty())
                return null;
            else {
                return citas.get(0);
            }
    }
    @Transactional
    public void  finalizarById(int cita){
        this.repository.finalizar(LocalDateTime.now(),cita);
    }

    }
