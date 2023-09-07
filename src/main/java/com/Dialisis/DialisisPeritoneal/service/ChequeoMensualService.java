package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.mapper.ChequeoMensualInDtoToChequeoMensual;
import com.Dialisis.DialisisPeritoneal.mapper.VisitaEspecialistaInDtoToVisitaEspecialista;
import com.Dialisis.DialisisPeritoneal.persistence.entity.ChequeoMensual;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Recambio;
import com.Dialisis.DialisisPeritoneal.persistence.entity.VisitaEspecialista;
import com.Dialisis.DialisisPeritoneal.persistence.repository.ChequeoMensualRepository;
import com.Dialisis.DialisisPeritoneal.persistence.repository.VisitaEspecialistaRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.ChequeoMensualInDto;
import com.Dialisis.DialisisPeritoneal.service.dto.VisitaEspecialistaInDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ChequeoMensualService {

    private final ChequeoMensualRepository repository;
    private final ChequeoMensualInDtoToChequeoMensual mapper;

    public ChequeoMensualService(ChequeoMensualRepository repository, ChequeoMensualInDtoToChequeoMensual mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ChequeoMensual crearChequeo(ChequeoMensualInDto chequeoMensualInDto) {
        ChequeoMensual chequeoMensual = mapper.map(chequeoMensualInDto);
        return this.repository.save(chequeoMensual);
    }

    public ChequeoMensual findUltimoChequeo(int idCita) {
        return this.repository.findUltimoChequeoMensual(idCita).get(0);
    }

    @Transactional
    public void actualizarChequeo(int id_chequeo_mensual, ChequeoMensualInDto chequeoMensualInDto){
        ChequeoMensual chequeoMensual= mapper.map(chequeoMensualInDto);
        chequeoMensual.setIdChequeoMensual(id_chequeo_mensual);
        this.repository.save(chequeoMensual);
    }
}
