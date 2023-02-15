package com.Dialisis.DialisisPeritoneal.service;
import com.Dialisis.DialisisPeritoneal.mapper.CuidadorInDtoToCuidador;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Cuidador;
import com.Dialisis.DialisisPeritoneal.persistence.repository.CuidadorRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.CuidadorInDto;
import org.springframework.stereotype.Service;

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

    public Cuidador findAllBycedula(long cedula){
        return this.cuidadorRepository.findAllByCedula(cedula);
    }


}
