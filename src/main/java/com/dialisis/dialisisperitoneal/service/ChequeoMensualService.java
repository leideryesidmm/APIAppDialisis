package com.dialisis.dialisisperitoneal.service;

import com.dialisis.dialisisperitoneal.mapper.ChequeoMensualInDtoToChequeoMensual;
import com.dialisis.dialisisperitoneal.persistence.entity.ChequeoMensual;
import com.dialisis.dialisisperitoneal.persistence.entity.Cita;
import com.dialisis.dialisisperitoneal.persistence.repository.ChequeoMensualRepository;
import com.dialisis.dialisisperitoneal.service.dto.ChequeoMensualInDto;
import com.dialisis.dialisisperitoneal.service.encryption.EncryptionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChequeoMensualService {

    private final ChequeoMensualRepository repository;
    private final ChequeoMensualInDtoToChequeoMensual mapper;
    private final EncryptionService encryptionService;


    public ChequeoMensualService(ChequeoMensualRepository repository, ChequeoMensualInDtoToChequeoMensual mapper, EncryptionService encryptionService) {
        this.repository = repository;
        this.mapper = mapper;
        this.encryptionService = encryptionService;
    }

    public ChequeoMensual crearChequeo(ChequeoMensualInDto chequeoMensualInDto) {
        ChequeoMensual chequeoMensual = mapper.map(chequeoMensualInDto);
        chequeoMensual= encryptionService.getEncFrontend().getChequeo().desencriptar(chequeoMensual);
        chequeoMensual= encryptionService.getEncBackend().getChequeo().encriptar(chequeoMensual);
        chequeoMensual= this.repository.save(chequeoMensual);
        chequeoMensual= encryptionService.getEncBackend().getChequeo().desencriptar(chequeoMensual);
        chequeoMensual= encryptionService.getEncFrontend().getChequeo().encriptar(chequeoMensual);
        return chequeoMensual;
    }

    public ChequeoMensual findUltimoChequeo(int idCita) {
        List<ChequeoMensual> chequeos=this.repository.findUltimoChequeoMensual(idCita);
        ChequeoMensual chequeo;
        if(chequeos.isEmpty())
            return null;
        else{
            chequeo=chequeos.get(0);
            chequeo=encryptionService.getEncBackend().getChequeo().desencriptar(chequeo);
            chequeo=encryptionService.getEncFrontend().getChequeo().encriptar(chequeo);
            return chequeo;
        }
    }

    @Transactional
    public void actualizarChequeo(int idChequeoMensual, ChequeoMensualInDto chequeoMensualInDto){
        ChequeoMensual chequeoMensual= mapper.map(chequeoMensualInDto);
        chequeoMensual.setIdChequeoMensual(idChequeoMensual);
        chequeoMensual=encryptionService.getEncFrontend().getChequeo().desencriptar(chequeoMensual);
        chequeoMensual=encryptionService.getEncBackend().getChequeo().encriptar(chequeoMensual);
        this.repository.save(chequeoMensual);
    }

    public List<ChequeoMensual> findAllChequeos(List<Cita> citas){
        List<ChequeoMensual> chequeos=new ArrayList<>();
        for (Cita cita:citas){
            cita=encryptionService.getEncFrontend().getCita().desencriptar(cita);
            cita=encryptionService.getEncBackend().getCita().encriptar(cita);
            ChequeoMensual chequeoM=this.repository.findByCita(cita);
            if(chequeoM!=null) {
                chequeoM = encryptionService.getEncBackend().getChequeo().desencriptar(chequeoM);
                chequeoM = encryptionService.getEncFrontend().getChequeo().encriptar(chequeoM);
                chequeos.add(chequeoM);
            }
        }
            return chequeos;
    }
}
