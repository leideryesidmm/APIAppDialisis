package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.mapper.MedicoInDtoToMedico;
import com.Dialisis.DialisisPeritoneal.mapper.PrescripcionInDtoToPrescripcion;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Medico;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Prescripcion;
import com.Dialisis.DialisisPeritoneal.persistence.repository.MedicoRepository;
import com.Dialisis.DialisisPeritoneal.persistence.repository.PrescripcionRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.MedicoInDto;
import com.Dialisis.DialisisPeritoneal.service.dto.PrescripcionInDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {
    private final MedicoRepository medicoRepository;
    private final PrescripcionRepository prescripcionRepository;
    private final PrescripcionInDtoToPrescripcion mapper2;
    private final MedicoInDtoToMedico mapper;

    public MedicoService(PrescripcionInDtoToPrescripcion mapper2,PrescripcionRepository prescripcionRepository,MedicoRepository medicoRepository, MedicoInDtoToMedico mapper) {
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
        return this.prescripcionRepository.save(prescripcion);
    }
}
