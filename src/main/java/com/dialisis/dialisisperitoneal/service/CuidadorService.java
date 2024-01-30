package com.dialisis.dialisisperitoneal.service;
import com.dialisis.dialisisperitoneal.mapper.CuidadorInDtoToCuidador;
import com.dialisis.dialisisperitoneal.persistence.entity.Cuidador;
import com.dialisis.dialisisperitoneal.persistence.repository.CuidadorRepository;
import com.dialisis.dialisisperitoneal.service.dto.CuidadorInDto;
import com.dialisis.dialisisperitoneal.service.encryption.EncryptionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CuidadorService {
    private final CuidadorRepository cuidadorRepository;
    private final CuidadorInDtoToCuidador cuidadorInDTOtoCuidador;
    private final EncryptionService encryptionService;


    public CuidadorService(CuidadorRepository cuidadorRepository, CuidadorInDtoToCuidador cuidadorInDTOtoCuidador, EncryptionService encryptionService) {
        this.cuidadorRepository = cuidadorRepository;
        this.cuidadorInDTOtoCuidador = cuidadorInDTOtoCuidador;
        this.encryptionService = encryptionService;
    }
    public Cuidador crearoActualizarCuidador(CuidadorInDto cuidadorInDto){
        Cuidador cuidador=cuidadorInDTOtoCuidador.map(cuidadorInDto);
        cuidador=encryptionService.getEncFrontend().getCuidador().desencriptar(cuidador);
        cuidador=encryptionService.getEncBackend().getCuidador().encriptar(cuidador);
        cuidador=this.cuidadorRepository.save(cuidador);
        Cuidador cuidador2=encryptionService.getEncBackend().getCuidador().desencriptar(new Cuidador(cuidador));
        Cuidador cuidador3=new Cuidador(encryptionService.getEncFrontend().getCuidador().encriptar(cuidador2));
        return cuidador3;
    }

    public List<Cuidador> findAll(){
        List<Cuidador> cuidadores= this.cuidadorRepository.findAll();
        for (int i = 0; i < cuidadores.size(); i++) {
            Cuidador cuidador=cuidadores.get(i);
            cuidador=encryptionService.getEncBackend().getCuidador().desencriptar(cuidador);
            cuidador=encryptionService.getEncFrontend().getCuidador().encriptar(cuidador);
            cuidadores.set(i,cuidador);
        }
        return cuidadores;
    }

    public Cuidador findAllBycedula(String cedula){
        Cuidador cuidador= new Cuidador(cedula);
        cuidador=encryptionService.getEncFrontend().getCuidador().desencriptar(cuidador);
        cuidador=encryptionService.getEncBackend().getCuidador().encriptar(cuidador);
        cuidador=this.cuidadorRepository.findAllByCedulaCuidador(cuidador.getCedulaCuidador());
        cuidador=encryptionService.getEncBackend().getCuidador().desencriptar(cuidador);
        cuidador=encryptionService.getEncFrontend().getCuidador().encriptar(cuidador);
        return cuidador;
    }
    @Transactional
    public Cuidador actualizarCuidador(String cedula,CuidadorInDto cuidadorInDto) {
        try{
        Cuidador cuidador = cuidadorInDTOtoCuidador.map(cuidadorInDto);
        cuidador.setCedulaCuidador(cedula);
            System.out.println(cuidador);
        cuidador=encryptionService.getEncFrontend().getCuidador().desencriptar(new Cuidador(cuidador));
            System.out.println(cuidador);
        cuidador=encryptionService.getEncBackend().getCuidador().encriptar(new Cuidador(cuidador));
            System.out.println(cuidador);
        cuidador=this.cuidadorRepository.save(cuidador);
            System.out.println(cuidador);
        cuidador=encryptionService.getEncBackend().getCuidador().desencriptar(new Cuidador(cuidador));
            System.out.println(cuidador);
        cuidador=encryptionService.getEncFrontend().getCuidador().encriptar(new Cuidador(cuidador));
            System.out.println(cuidador);
        return cuidador;}
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
