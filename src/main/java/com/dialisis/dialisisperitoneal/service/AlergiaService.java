package com.dialisis.dialisisperitoneal.service;

import com.dialisis.dialisisperitoneal.exceptions.ExceptionsEntitys;
import com.dialisis.dialisisperitoneal.mapper.AlergiaInDtoToAlergia;
import com.dialisis.dialisisperitoneal.persistence.entity.Alergia;
import com.dialisis.dialisisperitoneal.persistence.repository.AlergiaRepository;
import com.dialisis.dialisisperitoneal.service.dto.AlergiaInDto;
import com.dialisis.dialisisperitoneal.service.encryption.EncryptionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlergiaService {

    private final AlergiaRepository repository;
    private final AlergiaInDtoToAlergia mapper;
    private final EncryptionService encryptionService;



    private final ExceptionsEntitys exceptionsEntitysAlergia;

    public AlergiaService(AlergiaRepository repositorio, AlergiaInDtoToAlergia mapper, EncryptionService encryptionService, ExceptionsEntitys exceptionsEntitysAlergia) {
        this.repository = repositorio;
        this.mapper = mapper;
        this.encryptionService = encryptionService;
        this.exceptionsEntitysAlergia = exceptionsEntitysAlergia;
    }

    public Alergia crearAlergia(AlergiaInDto alergiaInDto){
            Alergia alergia = mapper.map(alergiaInDto);
            alergia=encryptionService.getEncFrontend().getAlergia().desencriptar(alergia);
            alergia=encryptionService.getEncBackend().getAlergia().encriptar(alergia);
            alergia=this.repository.save(alergia);

        Alergia alergiaPac=new Alergia(alergia.getIdAlergia());
        alergiaPac.setNombre(alergia.getNombre());
        alergiaPac=encryptionService.getEncBackend().getAlergia().desencriptar(alergiaPac);
        alergiaPac=encryptionService.getEncFrontend().getAlergia().encriptar(alergiaPac);
            return alergiaPac;
    }

    public List<Alergia> findAll(){
        List<Alergia> alergiasBack=this.repository.findAll();
        List<Alergia> alergias= new ArrayList<>();
        for (Alergia alergia : alergiasBack) {
            alergia=encryptionService.getEncBackend().getAlergia().desencriptar(alergia);
            alergia=encryptionService.getEncFrontend().getAlergia().encriptar(alergia);
            alergias.add(alergia);
        }
        return alergias;
    }
    public Alergia findById(int idAlergia){
        exceptionsEntitysAlergia.errorId(idAlergia);
        return this.repository.findById(idAlergia);
    }
    @Transactional
    public Alergia actualizarAlergia(int idAlergia, AlergiaInDto alergiaInDto){
       Alergia alergia=mapper.map(alergiaInDto);
       alergia.setIdAlergia(idAlergia);
       alergia=encryptionService.getEncFrontend().getAlergia().desencriptar(alergia);
       alergia=encryptionService.getEncBackend().getAlergia().encriptar(alergia);
       alergia=this.repository.save(alergia);
       Alergia alergiaPac= new Alergia(alergia.getIdAlergia());
       alergiaPac.setNombre(alergia.getNombre());
       alergiaPac=encryptionService.getEncBackend().getAlergia().desencriptar(alergiaPac);
       alergiaPac=encryptionService.getEncFrontend().getAlergia().encriptar(alergiaPac);
       return alergiaPac;
}
}
