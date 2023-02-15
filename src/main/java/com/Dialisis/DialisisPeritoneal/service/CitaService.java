package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.mapper.CitaInDtoToCita;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Cita;
import com.Dialisis.DialisisPeritoneal.persistence.entity.FormulaMedicamento;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Paciente;
import com.Dialisis.DialisisPeritoneal.persistence.repository.CitaRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.CitaInDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLOutput;
import java.time.*;
import java.util.Date;
import java.util.List;

@Service
public class CitaService {

    private final CitaRepository repository;
    private final CitaInDtoToCita mapper;


    public CitaService(CitaRepository repository, CitaInDtoToCita mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Cita crearCita(CitaInDto citaInDto){
        Cita cita = mapper.map(citaInDto);
        return this.repository.save(cita);
    }

    public List<Cita> findAllByPaciente(Paciente cedula){
        return this.repository.findAllByPaciente(cedula);
    }
    public Cita findById(int id_cita){
        return this.repository.findById(id_cita);
    }
    @Transactional
    public void actualizarCita(int id_cita,CitaInDto citaInDto){
        this.repository.actualizarCita(id_cita,citaInDto.getNombre_medico(),citaInDto.getPaciente(),citaInDto.getEspecialidad_medico(),citaInDto.getLugar(),citaInDto.getDireccion(),citaInDto.getFecha());
    }
    public void deleteById(int id_cita){
        Cita cita=findById(id_cita);
        LocalDateTime hoy=LocalDateTime.now();
        LocalDateTime citaf=cita.getFecha();
        if(hoy.isBefore(citaf)){
            this.repository.deleteById(id_cita);
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


}
