package com.dialisis.dialisisperitoneal.service;

import com.dialisis.dialisisperitoneal.exceptions.ToDoExceptions;
import com.dialisis.dialisisperitoneal.mapper.PacienteAlergiaInDtoToPacienteAlergia;
import com.dialisis.dialisisperitoneal.persistence.entity.Alergia;
import com.dialisis.dialisisperitoneal.persistence.entity.Paciente;
import com.dialisis.dialisisperitoneal.persistence.entity.PacienteAlergia;
import com.dialisis.dialisisperitoneal.persistence.repository.PacienteAlergiaRepository;
import com.dialisis.dialisisperitoneal.service.dto.PacienteAlergiaInDto;
import com.dialisis.dialisisperitoneal.service.encryption.EncryptionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteAlergiaService {

    private final PacienteAlergiaRepository repository;
    private final PacienteAlergiaInDtoToPacienteAlergia mapper;
    private EncryptionService encryptionService;

    public PacienteAlergiaService(PacienteAlergiaRepository repository, PacienteAlergiaInDtoToPacienteAlergia mapper, EncryptionService encryptionService) {
        this.repository = repository;
        this.mapper = mapper;
        this.encryptionService = encryptionService;
    }

    public PacienteAlergia crearPacienteAlergia(PacienteAlergiaInDto pacienteAlergiaInDto){
        PacienteAlergia pacienteAlergia= mapper.map(pacienteAlergiaInDto);
        pacienteAlergia.setActivo(true);
        pacienteAlergia=encryptionService.getEncFrontend().getPacienteAlergia().desencriptar(pacienteAlergia);
        pacienteAlergia=encryptionService.getEncBackend().getPacienteAlergia().encriptar(pacienteAlergia);
        pacienteAlergia= this.repository.save(pacienteAlergia);
        pacienteAlergia=encryptionService.getEncBackend().getPacienteAlergia().desencriptar(pacienteAlergia);
        return encryptionService.getEncFrontend().getPacienteAlergia().encriptar(pacienteAlergia);
    }

    public List<PacienteAlergia> findAll(){

        List<PacienteAlergia> pacienteAlergias= this.repository.findAll();
            for(int i=0;i<pacienteAlergias.size();i++) {
                PacienteAlergia pacienteAlergia = encryptionService.getEncBackend().getPacienteAlergia().desencriptar(pacienteAlergias.get(i));
                pacienteAlergia=encryptionService.getEncFrontend().getPacienteAlergia().encriptar(pacienteAlergia);
                pacienteAlergias.set(i,pacienteAlergia);
            }
            return pacienteAlergias;
    }

    public PacienteAlergia findById(int idPacienteAlergia){
        Optional<PacienteAlergia> optionalPacienteAlergia= this.repository.findById(idPacienteAlergia);
            PacienteAlergia pacienteAlergia = optionalPacienteAlergia.get();
            pacienteAlergia = encryptionService.getEncBackend().getPacienteAlergia().desencriptar(pacienteAlergia);
            pacienteAlergia=encryptionService.getEncFrontend().getPacienteAlergia().encriptar(pacienteAlergia);
            optionalPacienteAlergia = Optional.of(pacienteAlergia);
        return optionalPacienteAlergia.get();
    }
    @Transactional
    public void actualizarPacienteAlergia(int idPacienteAlergia, String paciente, int alergia, boolean activa){
        Paciente pac=new Paciente();
        pac.setCedula(paciente);
        pac=encryptionService.getEncFrontend().getPaciente().desencriptar(pac);
        pac=encryptionService.getEncBackend().getPaciente().encriptar(pac);
        Alergia aler= new Alergia(alergia);
        aler=encryptionService.getEncFrontend().getAlergia().desencriptar(aler);
        aler=encryptionService.getEncFrontend().getAlergia().encriptar(aler);

        this.repository.actualizarPacienteAlergia(idPacienteAlergia,pac.getCedula(),aler.getIdAlergia(), activa);
    }
    public List<PacienteAlergia> findAllByPaciente(String cedula){
        Paciente paciente=new Paciente(cedula);
        paciente=encryptionService.getEncFrontend().getPaciente().desencriptar(paciente);
        paciente=encryptionService.getEncBackend().getPaciente().encriptar(paciente);
        List<PacienteAlergia> pa= this.repository.findAllByPaciente(paciente.getCedula());
            for(int i=0;i<pa.size();i++) {
                PacienteAlergia pacienteAlergia = encryptionService.getEncBackend().getPacienteAlergia().desencriptar(pa.get(i));
                pacienteAlergia=encryptionService.getEncFrontend().getPacienteAlergia().encriptar(pacienteAlergia);
                pa.set(i,pacienteAlergia);
            }
            return pa;
    }
    public PacienteAlergia findAlergiaPorPaciente(String cedula,int idPacienteAlergia){
        Paciente paciente= new Paciente(cedula);
        paciente= encryptionService.getEncFrontend().getPaciente().desencriptar(paciente);
        paciente=encryptionService.getEncBackend().getPaciente().encriptar(paciente);
        PacienteAlergia pa=this.repository.findAlergiaPaciente(paciente.getCedula(),idPacienteAlergia);
        pa=encryptionService.getEncBackend().getPacienteAlergia().desencriptar(pa);
        return encryptionService.getEncFrontend().getPacienteAlergia().encriptar(pa);
    }
    @Transactional
    public void inactivarAlergia(String cedula,int idPacienteAlergia){
        Paciente paciente= new Paciente(cedula);
        paciente=encryptionService.getEncFrontend().getPaciente().desencriptar(paciente);
        paciente=encryptionService.getEncFrontend().getPaciente().encriptar(paciente);
        this.repository.inactivarAlergia(paciente.getCedula(),idPacienteAlergia);
    }
    @Transactional
    public void activarAlergia(String cedula,int idAlergia){
        Paciente paciente= new Paciente(cedula);
        paciente=encryptionService.getEncFrontend().getPaciente().desencriptar(paciente);
        paciente=encryptionService.getEncFrontend().getPaciente().encriptar(paciente);
        this.repository.activarAlergia(paciente.getCedula(),idAlergia);
    }

    public List<PacienteAlergia> findAlergiasPasadas(String cedula){
        Paciente paciente= new Paciente(cedula);
        paciente=encryptionService.getEncFrontend().getPaciente().desencriptar(paciente);
        paciente=encryptionService.getEncFrontend().getPaciente().encriptar(paciente);
        List<PacienteAlergia> pacienteAlergias= this.repository.findAlergiasPasadas(paciente.getCedula());
            for (int i=0;i<pacienteAlergias.size();i++){
                PacienteAlergia pa=encryptionService.getEncBackend().getPacienteAlergia().desencriptar(pacienteAlergias.get(i));
                pa=encryptionService.getEncFrontend().getPacienteAlergia().encriptar(pa);
                pacienteAlergias.set(i,pa);
            }
            return  pacienteAlergias;
    }
}
