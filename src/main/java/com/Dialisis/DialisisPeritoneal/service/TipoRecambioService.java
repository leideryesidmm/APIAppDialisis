package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.mapper.TipoRecambioInDtoToTipoRecambio;
import com.Dialisis.DialisisPeritoneal.persistence.entity.TipoRecambio;
import com.Dialisis.DialisisPeritoneal.persistence.repository.TipoRecambioRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.TipoRecambioInDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TipoRecambioService {

    private final TipoRecambioRepository repository;
    private final TipoRecambioInDtoToTipoRecambio mapper;

    public TipoRecambioService(TipoRecambioRepository repository, TipoRecambioInDtoToTipoRecambio mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public TipoRecambio crearTipoRecambio(TipoRecambioInDto tipoRecambioInDto){
        TipoRecambio tipoRecambio = mapper.map(tipoRecambioInDto);
        return this.repository.save(tipoRecambio);
    }

    public List<TipoRecambio> findAll(){
        return this.repository.findAll();
    }

    public TipoRecambio findById(int id_tipo){

        return  this.repository.findById(id_tipo);
    }
@Transactional
    public void actualizarTipoRecambio(int id_tipo, String descripcion){
        this.repository.actualizarTipoRecambio(id_tipo,descripcion);
    }
}
