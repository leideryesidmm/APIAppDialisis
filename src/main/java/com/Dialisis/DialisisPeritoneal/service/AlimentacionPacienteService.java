package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.exceptions.ToDoExceptions;
import com.Dialisis.DialisisPeritoneal.mapper.AlimentacionPacienteInDtoToAlimentacionPaciente;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Alergia;
import com.Dialisis.DialisisPeritoneal.persistence.entity.AlimentacionPaciente;
import com.Dialisis.DialisisPeritoneal.persistence.repository.AlimentacionPacienteRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.AlimentacionPacienteInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AlimentacionPacienteService {

    private final AlimentacionPacienteRepository repository;
    private final AlimentacionPacienteInDtoToAlimentacionPaciente mapper;

    public AlimentacionPacienteService(AlimentacionPacienteRepository repository, AlimentacionPacienteInDtoToAlimentacionPaciente mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public AlimentacionPaciente crearAlimentacionPaciente(AlimentacionPacienteInDto alimentacionPacienteInDto){
        AlimentacionPaciente alimentacionPaciente= mapper.map(alimentacionPacienteInDto);
        return this.repository.save(alimentacionPaciente);
    }

    public List<AlimentacionPaciente> findAll(){
        return this.repository.findAll();
    }

    public AlimentacionPaciente findById(int id_alimentacion_paciente){
        Optional<AlimentacionPaciente> optionalTAlimentacion = this.repository.findById(id_alimentacion_paciente);
        if (optionalTAlimentacion.isEmpty()) {
            throw new ToDoExceptions("Alimentacion de Paciente no encontrada", HttpStatus.NOT_FOUND);
        }
        return optionalTAlimentacion.get();
    }

    @Transactional
    public void actualizarAlimentacionPaciente(int idAlimentacionPaciente, AlimentacionPacienteInDto alimentacionPacienteInDto){
        this.repository.actualizarAlimentacionPaciente(idAlimentacionPaciente,
                alimentacionPacienteInDto.getJornada(),
                alimentacionPacienteInDto.getFecha_hora(),
                alimentacionPacienteInDto.getCantidad(),
                alimentacionPacienteInDto.getPaciente(),
                alimentacionPacienteInDto.getAlimentacion());
    }
    public List<AlimentacionPaciente> findAllByPaciente(long cedula){
        List<AlimentacionPaciente> listAlimentacion = this.repository.findAllByPaciente(cedula);
        if (listAlimentacion.isEmpty()) {
            throw new ToDoExceptions("cédula no encontrada", HttpStatus.NOT_FOUND);
        }
        return this.repository.findAllByPaciente(cedula);
    }
    public List<AlimentacionPaciente> findAllByPacienteByRango(long cedula, LocalDate fecha1, LocalDate fecha2){
        LocalTime hora_1=LocalTime.of(00,00,00);
        LocalTime hora_2=LocalTime.of(23,59,59);
        LocalDateTime fecha_1= LocalDateTime.of(fecha1, hora_1);
        LocalDateTime fecha_2= LocalDateTime.of(fecha2, hora_2);
        if(fecha_1.isAfter(fecha_2)){
            throw new ToDoExceptions("Rango de fechas inválido", HttpStatus.NOT_FOUND);
        }
        return this.repository.findAllByPacienteByRango(cedula,fecha_1,fecha_2);
    }
}
