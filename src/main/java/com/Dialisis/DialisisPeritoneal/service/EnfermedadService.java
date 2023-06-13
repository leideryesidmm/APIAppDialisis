package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.exceptions.ToDoExceptions;
import com.Dialisis.DialisisPeritoneal.mapper.EnfermedadInDtoToEnfermedad;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Enfermedad;
import com.Dialisis.DialisisPeritoneal.persistence.repository.EnfermedadRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.EnfermedadInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public Enfermedad findById(int idEnfermedad){
        Optional<Enfermedad> optionalEnfermedad = this.enfermedadRepository.findById(idEnfermedad);
        if (optionalEnfermedad.isEmpty()) {
            throw new ToDoExceptions("Enfermedad no encontrada", HttpStatus.NOT_FOUND);
        }
        return optionalEnfermedad.get();
    }

    public List<Enfermedad> findAll(){
        return this.enfermedadRepository.findAll();
    }
    @Transactional
    public void actualizarEnfermedad(int idEnfermedad, String nombre){
        this.enfermedadRepository.actualizarEnfermedad(idEnfermedad,nombre);
    }
}
