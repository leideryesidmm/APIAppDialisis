package com.dialisis.dialisisperitoneal.service;

import com.dialisis.dialisisperitoneal.mapper.RecambioHechoInDtoToRecambioHecho;
import com.dialisis.dialisisperitoneal.persistence.entity.Recambio;
import com.dialisis.dialisisperitoneal.persistence.entity.RecambioHecho;
import com.dialisis.dialisisperitoneal.persistence.repository.RecambioHechoRepository;
import com.dialisis.dialisisperitoneal.service.dto.RecambioHechoInDto;
import com.dialisis.dialisisperitoneal.service.encryption.EncryptionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RecambioHechoService {

    private final RecambioHechoRepository repository;
    private final RecambioHechoInDtoToRecambioHecho mapper;
    private EncryptionService encryptionService;

    public RecambioHechoService(RecambioHechoRepository repository, RecambioHechoInDtoToRecambioHecho mapper, EncryptionService encryptionService) {
        this.repository = repository;
        this.mapper = mapper;
        this.encryptionService = encryptionService;
    }

    public RecambioHecho crearRecambio(RecambioHechoInDto recambioHechoInDto){
        RecambioHecho recambioHecho= mapper.map(recambioHechoInDto);
        recambioHecho=encryptionService.getEncFrontend().getRecambioHecho().desencriptar(recambioHecho);
        recambioHecho=encryptionService.getEncBackend().getRecambioHecho().encriptar(recambioHecho);
        this.repository.save(recambioHecho);
        RecambioHecho recambio= new RecambioHecho();
        recambio=recambioHecho;
        recambio.setIdRecambioHecho(recambioHecho.getIdRecambioHecho());
        recambio.setRecambio(recambioHecho.getRecambio());
        recambio.setFecha(recambioHecho.getFecha());

        recambio=encryptionService.getEncBackend().getRecambioHecho().desencriptar(recambio);
        return encryptionService.getEncFrontend().getRecambioHecho().encriptar(recambio);

    }
    public RecambioHecho editarRecambio(RecambioHechoInDto recambioHechoInDto, int idRecambioHecho){
        RecambioHecho recambioHecho1=this.findRecambioById(idRecambioHecho);
        RecambioHecho recambioHecho= mapper.map(recambioHechoInDto);
        recambioHecho.setIdRecambioHecho(idRecambioHecho);
        recambioHecho.setRecambio(recambioHecho1.getRecambio());
        recambioHecho=encryptionService.getEncFrontend().getRecambioHecho().desencriptar(recambioHecho);
        recambioHecho=encryptionService.getEncBackend().getRecambioHecho().encriptar(recambioHecho);
        this.repository.save(recambioHecho);
        RecambioHecho recambio=new RecambioHecho();
        recambio.setIdRecambioHecho(recambioHecho.getIdRecambioHecho());
        //pasarle lo de recambioHecho
        recambio=encryptionService.getEncBackend().getRecambioHecho().desencriptar(recambio);
        return encryptionService.getEncFrontend().getRecambioHecho().encriptar(recambio);

    }
    public List<RecambioHecho> findByRecambio(Recambio recambio){
        recambio=encryptionService.getEncFrontend().getRecambio().desencriptar(recambio);
        recambio=encryptionService.getEncBackend().getRecambio().encriptar(recambio);
        List<RecambioHecho> rec=this.repository.findByRecambio(recambio);
            for (int i = 0; i < rec.size(); i++) {
                RecambioHecho rh=encryptionService.getEncBackend().getRecambioHecho().desencriptar(rec.get(i));
                rh=encryptionService.getEncFrontend().getRecambioHecho().encriptar(rh);
                rec.set(i,rh);
            }
            return rec;
    }

    public RecambioHecho findByRecambioAndFecha(int recambio, LocalDate fecha){
        RecambioHecho rh=this.repository.findByRecambioAndFecha(recambio,fecha);
        rh=encryptionService.getEncBackend().getRecambioHecho().desencriptar(rh);
        return encryptionService.getEncFrontend().getRecambioHecho().encriptar(rh);
    }

    public RecambioHecho findRecambioById(int idRecambioHecho){
        RecambioHecho rh=this.repository.findById(idRecambioHecho);
        rh=encryptionService.getEncBackend().getRecambioHecho().desencriptar(rh);
        return encryptionService.getEncFrontend().getRecambioHecho().encriptar(rh);
    }
}
