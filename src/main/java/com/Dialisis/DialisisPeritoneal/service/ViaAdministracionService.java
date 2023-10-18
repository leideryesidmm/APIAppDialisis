package com.dialisis.dialisisperitoneal.service;

import com.dialisis.dialisisperitoneal.exceptions.ToDoExceptions;
import com.dialisis.dialisisperitoneal.mapper.ViaAdministracionInDtoToViaAdministracion;
import com.dialisis.dialisisperitoneal.persistence.entity.ViaAdministracion;
import com.dialisis.dialisisperitoneal.persistence.repository.ViaAdministracionRepository;
import com.dialisis.dialisisperitoneal.service.dto.ViaAdministracionInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViaAdministracionService {
    private final ViaAdministracionRepository repository;
    private final ViaAdministracionInDtoToViaAdministracion mapper;

    public ViaAdministracionService(ViaAdministracionRepository repository, ViaAdministracionInDtoToViaAdministracion mapper) {
        this.repository = repository;
        this.mapper = mapper;

    }

    public ViaAdministracion findbyId(int id){
        Optional<ViaAdministracion> viaAdministracionOpcional= this.repository.findById(id);
        if (viaAdministracionOpcional.isEmpty()) {
            throw new ToDoExceptions("Vía Administración no encontrada", HttpStatus.NOT_FOUND);
        }
        return viaAdministracionOpcional.get();
    }

    public List<ViaAdministracion> findAll(){
        return this.repository.findAll();
    }
    public ViaAdministracion createoViaAdministracion(ViaAdministracionInDto viaAdministracionInDto){
        ViaAdministracion viaAdministracion=mapper.map(viaAdministracionInDto);
        return this.repository.save(viaAdministracion);
    }
    public ViaAdministracion updateViaAdministracion(int id,ViaAdministracionInDto viaAdministracionInDto){
        ViaAdministracion viaAdministracion=mapper.map(viaAdministracionInDto);
        viaAdministracion.setIdViaAdministracion(id);
        return this.repository.save(viaAdministracion);
    }

}
