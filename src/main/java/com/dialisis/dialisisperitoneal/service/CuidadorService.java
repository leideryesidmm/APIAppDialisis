package com.dialisis.dialisisperitoneal.service;
import com.dialisis.dialisisperitoneal.mapper.CuidadorInDtoToCuidador;
import com.dialisis.dialisisperitoneal.persistence.entity.Cuidador;
import com.dialisis.dialisisperitoneal.persistence.repository.CuidadorRepository;
import com.dialisis.dialisisperitoneal.service.dto.CuidadorInDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CuidadorService {
    private final CuidadorRepository cuidadorRepository;
    private final CuidadorInDtoToCuidador cuidadorInDTOtoCuidador;

    public CuidadorService(CuidadorRepository cuidadorRepository, CuidadorInDtoToCuidador cuidadorInDTOtoCuidador) {
        this.cuidadorRepository = cuidadorRepository;
        this.cuidadorInDTOtoCuidador = cuidadorInDTOtoCuidador;
    }
    public Cuidador crearoActualizarCuidador(CuidadorInDto cuidadorInDto){
        Cuidador cuidador=cuidadorInDTOtoCuidador.map(cuidadorInDto);
        return this.cuidadorRepository.save(cuidador);
    }

    public List<Cuidador> findAll(){
        return this.cuidadorRepository.findAll();
    }

    public Cuidador findAllBycedula(String cedula){
        return this.cuidadorRepository.findAllByCedulaCuidador(cedula);
    }
    @Transactional
    public Cuidador actualizarCuidador(String cedula,CuidadorInDto cuidadorInDto) {
        Cuidador cuidador = cuidadorInDTOtoCuidador.map(cuidadorInDto);
        cuidador.setCedulaCuidador(cedula);
        return this.cuidadorRepository.save(cuidador);
    }

}
