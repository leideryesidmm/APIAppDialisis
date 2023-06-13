package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.exceptions.ToDoExceptions;
import com.Dialisis.DialisisPeritoneal.mapper.MedicoClinicaInDtoToMedicoClinica;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Medicamento;
import com.Dialisis.DialisisPeritoneal.persistence.entity.MedicoClinica;
import com.Dialisis.DialisisPeritoneal.persistence.repository.MedicoClinicaRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.MedicoClinicaInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MedicoClinicaService {

    private final MedicoClinicaRepository repository;

    private final MedicoClinicaInDtoToMedicoClinica mapper;

    public MedicoClinicaService(MedicoClinicaRepository repository, MedicoClinicaInDtoToMedicoClinica mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public MedicoClinica crearMedicoClinica(MedicoClinicaInDto medicoClinicaInDto){
        MedicoClinica medicoClinica= mapper.map(medicoClinicaInDto);
        return this.repository.save(medicoClinica);
    }

    public List<MedicoClinica> findAll(){
        return this.repository.findAll();
    }

    public MedicoClinica findById(int idMedicoClinica){
        Optional<MedicoClinica> optionalMedicoClinica= this.repository.findById(idMedicoClinica);
        if (optionalMedicoClinica.isEmpty()) {
            throw new ToDoExceptions("Clínica por médico no encontrada", HttpStatus.NOT_FOUND);
        }
        return optionalMedicoClinica.get();
    }

    @Transactional
    public void actualizarMedicoClinica(int idMedicoClinica, String medico, int clinica, boolean activa){
        this.repository.actualizarMedicoClinica(idMedicoClinica,medico,clinica, activa);
    }
    public List<MedicoClinica> findAllByMedico(String cedula){
        return this.repository.findAllByMedico(cedula);
    }
    public MedicoClinica findClinicaPorMedico(String cedula,int idClinica){
        return this.repository.findClinicaPorMedico(cedula,idClinica);
    }
    @Transactional
    public void activarClinica(String cedula,int idClinica){
        this.repository.activarClinica(cedula,idClinica);
    }
    @Transactional
    public void inactivarClinica(String cedula,int idClinica){
        this.repository.inactivarClinica(cedula,idClinica);
    }
    public List<MedicoClinica> findClinicasPasadas(String cedula){
        return this.repository.findClinicasPasadas(cedula);
    }

}
