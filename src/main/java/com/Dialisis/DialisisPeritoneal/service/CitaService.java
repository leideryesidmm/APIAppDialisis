package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.exceptions.ToDoExceptions;
import com.Dialisis.DialisisPeritoneal.mapper.CitaInDtoToCita;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Cita;
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
        System.out.println("antes del if");
        LocalDateTime now = LocalDateTime.now();
        //if(citaInDto.getFecha().isBefore(now)){
        //throw new ToDoExceptions("Fecha de la cita inválida", HttpStatus.BAD_REQUEST);
        //}
            Cita cita = mapper.map(citaInDto);
        System.out.println(cita);
            return this.repository.save(cita);
    }

    public List<Cita> findAllByPaciente(Paciente cedula){
        return this.repository.findAllByPaciente(cedula);
    }
    public Cita findById(int id_cita){
        Optional<Cita> optionalCita = this.repository.findById(id_cita);
        if (optionalCita.isEmpty()) {
            throw new ToDoExceptions("Cita no encontrada", HttpStatus.NOT_FOUND);
        }
        return optionalCita.get();
    }
    @Transactional
    public void actualizarCita(int id_cita,CitaInDto citaInDto){

        this.repository.actualizarCita(id_cita,citaInDto.getMedico(),citaInDto.getPaciente(),citaInDto.getDireccion(),citaInDto.getFecha(),citaInDto.getHora());
    }
    public void deleteById(int id_cita){
        Cita cita=findById(id_cita);
        LocalDateTime hoy=LocalDateTime.now();
        LocalDateTime citaf=cita.getFecha();
        Optional<Cita> optionalCita = this.repository.findById(id_cita);
        if (optionalCita.isEmpty()) {
            throw new ToDoExceptions("Cita no encontrada", HttpStatus.NOT_FOUND);
        }
        if(hoy.isBefore(citaf)){
            this.repository.deleteById(id_cita);
        }
        else{
                throw new ToDoExceptions("La cita ya pasó, no se puede eliminar", HttpStatus.NOT_FOUND);
        }
    }


    public List<Cita> findAllCitasAntiguasByPaciente(Paciente cedula){
        LocalDateTime hoy=LocalDateTime.now();
        return this.repository.findAllCitasAntiguasByPaciente(cedula,hoy);
    }
    public List<Cita> findAllCitasFuturasByPaciente(Paciente cedula){
        LocalDateTime hoy=LocalDateTime.now();
        return this.repository.findAllCitasFuturasByPaciente(cedula,hoy);
    }

    public void excepciones(CitaInDto citaInDto, BindingResult result){
        LocalDateTime now= LocalDateTime.now();
        if(citaInDto.getFecha().isBefore(now)){
            throw new ToDoExceptions("Fecha de la cita inválida", HttpStatus.BAD_REQUEST);
        }
               }

    }
