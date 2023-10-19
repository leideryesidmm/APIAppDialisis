package com.dialisis.dialisisperitoneal.service;

import com.dialisis.dialisisperitoneal.mapper.MedicoInDtoToMedico;
import com.dialisis.dialisisperitoneal.persistence.entity.Medico;
import com.dialisis.dialisisperitoneal.persistence.repository.MedicoRepository;
import com.dialisis.dialisisperitoneal.service.dto.MedicoInDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MedicoService {
    private final MedicoRepository medicoRepository;
    private final MedicoInDtoToMedico mapper;

    public MedicoService(MedicoRepository medicoRepository, MedicoInDtoToMedico mapper) {
        this.medicoRepository = medicoRepository;
        this.mapper = mapper;
    }

    public List<Medico> findAll(){
        return this.medicoRepository.findAll();
    }
    public Medico findByCedula(String cedula){
        return this.medicoRepository.findAllByCedula(cedula);
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


}
