package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.mapper.MedicoInDtoToMedico;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Medico;
import com.Dialisis.DialisisPeritoneal.persistence.repository.MedicoRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.MedicoInDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {
    private final MedicoRepository medicoRepository;
    private final MedicoInDtoToMedico mapper;

    public MedicoService(MedicoRepository medicoRepository, MedicoInDtoToMedico mapper) {
        this.medicoRepository = medicoRepository;
        this.mapper = mapper;
    }

    public Medico createoUpdateMedico(MedicoInDto medicoInDto){
        Medico medico=mapper.map(medicoInDto);
        return this.medicoRepository.save(medico);
    }
    public List<Medico> findAll(){
        return this.medicoRepository.findAll();
    }
    public Medico findByCedula(long cedula){
        return this.medicoRepository.findAllByCedula(cedula);
    }
}
