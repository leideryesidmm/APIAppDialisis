package com.Dialisis.DialisisPeritoneal.service;
import com.Dialisis.DialisisPeritoneal.mapper.CuidadorPacienteInDtoToCuidadorPaciente;
import com.Dialisis.DialisisPeritoneal.persistence.entity.CuidadorPaciente;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Paciente;
import com.Dialisis.DialisisPeritoneal.persistence.repository.CuidadorPacienteRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.CuidadorPacienteInDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class CuidadorPacienteService {
    private final CuidadorPacienteRepository repository;
    private final CuidadorPacienteInDtoToCuidadorPaciente mapper;

    public CuidadorPacienteService(CuidadorPacienteRepository repository, CuidadorPacienteInDtoToCuidadorPaciente mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public CuidadorPaciente crearCuidadorPaciente(CuidadorPacienteInDto cuidadorpacienteInDto){
        CuidadorPaciente cuidadorpaciente=this.mapper.map(cuidadorpacienteInDto);
        return this.repository.save(cuidadorpaciente);
    }
    public List<CuidadorPaciente> findAll(){
        return this.repository.findAll();
    }

    public List<CuidadorPaciente> findAllByPaciente(long paciente){

        return this.repository.findAllByPaciente(new Paciente(paciente));
    }

    public CuidadorPaciente findById(int id_cuidadorpaciente){
        return this.repository.findById(id_cuidadorpaciente);
    }

    public CuidadorPaciente findCuidadorActivo(long cedula){
        return this.repository.findCuidadorActivo(cedula);
    }
    public void actualizarCuidadorPaciente(int id_cuidador_paciente, Date fechaini,Date fecha_fin, boolean activo){
        this.repository.actualizarCuidadorPaciente(id_cuidador_paciente,fechaini,fecha_fin, activo);
    }
    @Transactional
    public void inactivarCuidador(int id_cuidador_paciente){
        LocalDate fecha_final= LocalDate.now();
        this.repository.inactivarCuidador(id_cuidador_paciente,fecha_final);
    }

}
