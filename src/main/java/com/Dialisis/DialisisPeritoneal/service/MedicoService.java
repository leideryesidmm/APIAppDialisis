package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.exceptions.ToDoExceptions;
import com.Dialisis.DialisisPeritoneal.mapper.MedicoInDtoToMedico;
import com.Dialisis.DialisisPeritoneal.mapper.PrescripcionInDtoToPrescripcion;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Medico;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Paciente;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Prescripcion;
import com.Dialisis.DialisisPeritoneal.persistence.repository.MedicoRepository;
import com.Dialisis.DialisisPeritoneal.persistence.repository.PrescripcionDiaRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.MedicoInDto;
import com.Dialisis.DialisisPeritoneal.service.dto.PacienteInDto;
import com.Dialisis.DialisisPeritoneal.service.dto.PrescripcionInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MedicoService {
    private final MedicoRepository medicoRepository;
    private final PrescripcionDiaRepository prescripcionRepository;
    private final PrescripcionInDtoToPrescripcion mapper2;
    private final MedicoInDtoToMedico mapper;

    public MedicoService(PrescripcionInDtoToPrescripcion mapper2, PrescripcionDiaRepository prescripcionRepository, MedicoRepository medicoRepository, MedicoInDtoToMedico mapper) {
        this.medicoRepository = medicoRepository;
        this.mapper = mapper;
        this.prescripcionRepository=prescripcionRepository;
        this.mapper2=mapper2;
    }

    public Medico createoUpdateMedico(MedicoInDto medicoInDto){
        Medico medico=mapper.map(medicoInDto);
        return this.medicoRepository.save(medico);
    }
    public List<Medico> findAll(){
        return this.medicoRepository.findAll();
    }
    public Medico findByCedula(String cedula){
        return this.medicoRepository.findAllByCedula(cedula);
    }

    public Prescripcion createoUpdatePrescripcion(PrescripcionInDto prescripcionInDto){
        Prescripcion prescripcion=mapper2.map(prescripcionInDto);
        return null;//this.prescripcionRepository.save(prescripcion);
    }


    public Medico crearMedico(MedicoInDto medicoInDto) {
        Medico medico = mapper.map(medicoInDto);
        return this.medicoRepository.save(medico);
    }

    @Transactional
    public void actualizarDatosMedico(MedicoInDto medicoInDto, Medico medico) {
        Medico med= mapper.map(medicoInDto);
        med.setCedula(medicoInDto.getCedula());
        this.medicoRepository.save(med);
    }

    @Transactional
    public void inactivarMedico(String cedula) {
        this.medicoRepository.inactivarMedico(cedula);
    }

    @Transactional
    public void activarMedico(String cedula) {
        this.medicoRepository.activarMedico(cedula);
    }
}
