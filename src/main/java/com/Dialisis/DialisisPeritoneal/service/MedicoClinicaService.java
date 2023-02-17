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

    public MedicoClinica findById(int id_medico_clinica){
        Optional<MedicoClinica> optionalMedicoClinica= this.repository.findById(id_medico_clinica);
        if (optionalMedicoClinica.isEmpty()) {
            throw new ToDoExceptions("Clínica por médico no encontrada", HttpStatus.NOT_FOUND);
        }
        return optionalMedicoClinica.get();
    }

    @Transactional
    public void actualizarMedicoClinica(int id_medico_clinica, long medico, int clinica, boolean activa){
        this.repository.actualizarMedicoClinica(id_medico_clinica,medico,clinica, activa);
    }
    public List<MedicoClinica> findAllByMedico(long cedula){
        return this.repository.findAllByMedico(cedula);
    }
    public MedicoClinica findClinicaPorMedico(long cedula,int id_clinica){
        return this.repository.findClinicaPorMedico(cedula,id_clinica);
    }
    @Transactional
    public void activarClinica(long cedula,int id_clinica){
        this.repository.activarClinica(cedula,id_clinica);
    }
    @Transactional
    public void inactivarClinica(long cedula,int id_clinica){
        this.repository.inactivarClinica(cedula,id_clinica);
    }
    public List<MedicoClinica> findClinicasPasadas(long cedula){
        return this.repository.findClinicasPasadas(cedula);
    }

}
