package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.mapper.ChequeoMensualInDtoToChequeoMensual;
import com.Dialisis.DialisisPeritoneal.mapper.VisitaEspecialistaInDtoToVisitaEspecialista;
import com.Dialisis.DialisisPeritoneal.persistence.entity.ChequeoMensual;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Cita;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Recambio;
import com.Dialisis.DialisisPeritoneal.persistence.entity.VisitaEspecialista;
import com.Dialisis.DialisisPeritoneal.persistence.repository.ChequeoMensualRepository;
import com.Dialisis.DialisisPeritoneal.persistence.repository.VisitaEspecialistaRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.ChequeoMensualInDto;
import com.Dialisis.DialisisPeritoneal.service.dto.VisitaEspecialistaInDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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
        List<ChequeoMensual> chequeos=this.repository.findUltimoChequeoMensual(idCita);
        if(chequeos.isEmpty())
            return null;
        else
            return chequeos.get(0);
    }

    @Transactional
    public void actualizarChequeo(int idChequeoMensual, ChequeoMensualInDto chequeoMensualInDto){
        ChequeoMensual chequeoMensual= mapper.map(chequeoMensualInDto);
        chequeoMensual.setIdChequeoMensual(idChequeoMensual);
        this.repository.save(chequeoMensual);
    }

    public List<ChequeoMensual> findAllChequeos(List<Cita> citas){
        List<ChequeoMensual> chequeos=new ArrayList<>();
        for (Cita cita:citas){
            ChequeoMensual chequeoM=this.repository.findByCita(cita);
            if(chequeoM!=null)
                chequeos.add(chequeoM);
        }
        if(chequeos.isEmpty())
            return null;
        else{
            return chequeos;}
    }
}
