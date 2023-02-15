package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.mapper.EnfermedadInDtoToEnfermedad;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Enfermedad;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Usuario;
import com.Dialisis.DialisisPeritoneal.persistence.repository.EnfermedadRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.EnfermedadInDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnfermedadService {
    private final EnfermedadRepository enfermedadRepository;
    private final EnfermedadInDtoToEnfermedad mapper;

    public EnfermedadService(EnfermedadRepository enfermedadRepository, EnfermedadInDtoToEnfermedad enfermedadInDTOtoEnfermedad) {
        this.enfermedadRepository = enfermedadRepository;
        this.mapper = enfermedadInDTOtoEnfermedad;
    }
    public Enfermedad crearEnfermedad(EnfermedadInDto pacienteInDto){
        Enfermedad paciente = mapper.map(pacienteInDto);
        return this.enfermedadRepository.save(paciente);
    }

    public Enfermedad findById(int id){
        return this.enfermedadRepository.findById(id);
    }

    public List<Enfermedad> findAll(){
        return this.enfermedadRepository.findAll();
    }
    @Transactional
    public void actualizarEnfermedad(int id_enfermedad, String nombre){
        this.enfermedadRepository.actualizarEnfermedad(id_enfermedad,nombre);
    }
}
