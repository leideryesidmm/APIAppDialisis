package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.mapper.MedicoInDtoToMedico;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Medico;
import com.Dialisis.DialisisPeritoneal.persistence.repository.MedicoRepository;
import com.Dialisis.DialisisPeritoneal.persistence.repository.PrescripcionDiaRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.MedicoInDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MedicoService {
    private final MedicoRepository medicoRepository;
    private final PrescripcionDiaRepository prescripcionRepository;
    private final MedicoInDtoToMedico mapper;

    public MedicoService( PrescripcionDiaRepository prescripcionRepository, MedicoRepository medicoRepository, MedicoInDtoToMedico mapper) {
        this.medicoRepository = medicoRepository;
        this.mapper = mapper;
        this.prescripcionRepository=prescripcionRepository;
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
