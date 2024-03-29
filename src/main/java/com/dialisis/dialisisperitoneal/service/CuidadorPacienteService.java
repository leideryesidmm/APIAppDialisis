package com.dialisis.dialisisperitoneal.service;
import com.dialisis.dialisisperitoneal.exceptions.ToDoExceptions;
import com.dialisis.dialisisperitoneal.mapper.CuidadorPacienteInDtoToCuidadorPaciente;
import com.dialisis.dialisisperitoneal.persistence.entity.Cuidador;
import com.dialisis.dialisisperitoneal.persistence.entity.CuidadorPaciente;
import com.dialisis.dialisisperitoneal.persistence.entity.Paciente;
import com.dialisis.dialisisperitoneal.persistence.repository.CuidadorPacienteRepository;
import com.dialisis.dialisisperitoneal.service.dto.CuidadorPacienteInDto;
import com.dialisis.dialisisperitoneal.service.encryption.EncryptionService;
import org.springframework.boot.web.embedded.netty.NettyWebServer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CuidadorPacienteService {
    private final CuidadorPacienteRepository repository;
    private final CuidadorPacienteInDtoToCuidadorPaciente mapper;
    private final EncryptionService encryptionService;

    public CuidadorPacienteService(CuidadorPacienteRepository repository, CuidadorPacienteInDtoToCuidadorPaciente mapper, EncryptionService encryptionService) {
        this.repository = repository;
        this.mapper = mapper;
        this.encryptionService = encryptionService;
    }

    public CuidadorPaciente crearCuidadorPaciente(CuidadorPacienteInDto cuidadorpacienteInDto){
        try{
            CuidadorPaciente cuidadorpaciente=this.mapper.map(cuidadorpacienteInDto);
            cuidadorpaciente=encryptionService.getEncFrontend().getCuidadorPaciente().desencriptar(cuidadorpaciente);
            cuidadorpaciente=encryptionService.getEncBackend().getCuidadorPaciente().encriptar(cuidadorpaciente);
             cuidadorpaciente.setActivo(true);
            this.repository.save(cuidadorpaciente);
            cuidadorpaciente=encryptionService.getEncBackend().getCuidadorPaciente().desencriptar(new CuidadorPaciente(cuidadorpaciente));
            cuidadorpaciente=encryptionService.getEncFrontend().getCuidadorPaciente().encriptar(new CuidadorPaciente(cuidadorpaciente));
            return cuidadorpaciente;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public List<CuidadorPaciente> findAll(){
        List<CuidadorPaciente> cuidadorPacientes= this.repository.findAll();
        for (int i = 0; i < cuidadorPacientes.size(); i++) {
            CuidadorPaciente cuidadorPaciente=cuidadorPacientes.get(i);
            cuidadorPaciente=encryptionService.getEncBackend().getCuidadorPaciente().desencriptar(cuidadorPaciente);
            cuidadorPaciente=encryptionService.getEncFrontend().getCuidadorPaciente().encriptar(cuidadorPaciente);
            cuidadorPacientes.set(i,cuidadorPaciente);
        }
        return cuidadorPacientes;
    }

    public List<CuidadorPaciente> findAllByPaciente(String paciente){
        Paciente paciente1=new Paciente(paciente);
        paciente1=encryptionService.getEncFrontend().getPaciente().desencriptar(paciente1);
        paciente1=encryptionService.getEncBackend().getPaciente().encriptar(paciente1);
        List<CuidadorPaciente> cuidadorPacientes= this.repository.findAllByPaciente(paciente1);
        List<CuidadorPaciente> cuidadorPacientes1= new ArrayList<>();
        CuidadorPaciente cuidadorPaciente=new CuidadorPaciente();
        for (int i = 0; i < cuidadorPacientes.size(); i++) {
            cuidadorPaciente=encryptionService.getEncBackend().getCuidadorPaciente().desencriptar(cuidadorPacientes.get(i));
            cuidadorPaciente=encryptionService.getEncFrontend().getCuidadorPaciente().encriptar(cuidadorPaciente);
            cuidadorPacientes1.add(cuidadorPaciente);
        }
        return cuidadorPacientes1;
    }
    public CuidadorPaciente findById(int idCuidadorpaciente){
        Optional<CuidadorPaciente> optionalCuidadorPaciente = this.repository.findById(idCuidadorpaciente);
        if (optionalCuidadorPaciente.isEmpty()) {
            throw new ToDoExceptions("Cormobilidad no encontrada", HttpStatus.NOT_FOUND);
        }
        CuidadorPaciente cuidadorPaciente=optionalCuidadorPaciente.get();
        cuidadorPaciente=encryptionService.getEncBackend().getCuidadorPaciente().desencriptar(cuidadorPaciente);
        cuidadorPaciente=encryptionService.getEncFrontend().getCuidadorPaciente().encriptar(cuidadorPaciente);
        return cuidadorPaciente;
    }

    public CuidadorPaciente findCuidadorActivo(String cedula){
        Paciente p =new Paciente(cedula);
        p=encryptionService.getEncFrontend().getPaciente().desencriptar(p);
        p=encryptionService.getEncBackend().getPaciente().encriptar(p);
        CuidadorPaciente cuidadorPaciente=this.repository.findCuidadorActivo(p.getCedula());
        cuidadorPaciente=encryptionService.getEncBackend().getCuidadorPaciente().desencriptar(cuidadorPaciente);
        cuidadorPaciente=encryptionService.getEncFrontend().getCuidadorPaciente().encriptar(cuidadorPaciente);
        return cuidadorPaciente;
    }
    public void actualizarCuidadorPaciente(int idCuidadorPaciente, Date fechaini,Date fechaFin, boolean activo){
        this.repository.actualizarCuidadorPaciente(idCuidadorPaciente,fechaini,fechaFin, activo);
    }
    @Transactional
    public void inactivarCuidador(CuidadorPaciente cuidadorPaciente){
        cuidadorPaciente=encryptionService.getEncFrontend().getCuidadorPaciente().desencriptar(cuidadorPaciente);
        cuidadorPaciente=encryptionService.getEncBackend().getCuidadorPaciente().encriptar(cuidadorPaciente);
        LocalDate fechaFinal= LocalDate.now();
        try {
            cuidadorPaciente.setActivo(false);
            cuidadorPaciente.setFechaFin(fechaFinal);
            this.repository.save(cuidadorPaciente);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
