package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.exceptions.ToDoExceptions;
import com.Dialisis.DialisisPeritoneal.mapper.CormobilidadInDtoToCormobilidad;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Alergia;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Cormobilidad;
import com.Dialisis.DialisisPeritoneal.persistence.entity.CuidadorPaciente;
import com.Dialisis.DialisisPeritoneal.persistence.repository.CormobilidadRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.CormobilidadInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CormobilidadService {

    private final CormobilidadRepository repository;
    private final CormobilidadInDtoToCormobilidad mapper;


    public CormobilidadService(CormobilidadRepository repository, CormobilidadInDtoToCormobilidad mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Cormobilidad crearCormobilidad(CormobilidadInDto cormobilidadInDto){
        Cormobilidad cormobilidad=mapper.map(cormobilidadInDto);
        return this.repository.save(cormobilidad);
    }

    public List<Cormobilidad> findAll(){
        return this.repository.findAll();
    }
    public Cormobilidad findById(int id_cormobilidad){
        Optional<Cormobilidad> optionalCormobilidad = this.repository.findById(id_cormobilidad);
        if (optionalCormobilidad.isEmpty()) {
            throw new ToDoExceptions("Cormobilidad no encontrada", HttpStatus.NOT_FOUND);
        }
        return optionalCormobilidad.get();
    }

    @Transactional
    public void actualizarCormobilidad(int id_cormobilidad, long paciente, int enfermedad){
        this.repository.actualizarCormobilidad(id_cormobilidad,paciente,enfermedad);
    }
    @Transactional
    public void inactivarCormobilidad(int id_cormobilidad){
        this.repository.inactivarCormobilidad(id_cormobilidad);
    }
    @Transactional
    public void activarCormobilidad(int id_cormobilidad){
        this.repository.activarCormobilidad(id_cormobilidad);
    }

    public List<Cormobilidad> findEnfermedadesPasadas(String paciente){
        return repository.findAllActivo(paciente);
    }
}
