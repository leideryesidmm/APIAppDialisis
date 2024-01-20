package com.dialisis.dialisisperitoneal.service;

import com.dialisis.dialisisperitoneal.mapper.PacienteInDtoToPaciente;
import com.dialisis.dialisisperitoneal.persistence.entity.Paciente;
import com.dialisis.dialisisperitoneal.persistence.entity.PacienteAlergia;
import com.dialisis.dialisisperitoneal.persistence.repository.PacienteRepository;
import com.dialisis.dialisisperitoneal.service.dto.PacienteInDto;
import com.dialisis.dialisisperitoneal.service.encryption.EncryptionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository repository;
    private final PacienteInDtoToPaciente mapper;
    private EncryptionService encryptionService;

    public PacienteService(PacienteRepository repository, PacienteInDtoToPaciente mapper, EncryptionService encryptionService) {
        this.repository = repository;
        this.mapper = mapper;
        this.encryptionService = encryptionService;
    }

    public Paciente crearPaciente(PacienteInDto pacienteInDto) {
        Paciente paciente = mapper.map(pacienteInDto);
        paciente = encryptionService.getEncFrontend().getPaciente().desencriptar(paciente);
        paciente = encryptionService.getEncBackend().getPaciente().encriptar(paciente);
        paciente = this.repository.save(paciente);
        paciente = encryptionService.getEncBackend().getPaciente().desencriptar(paciente);
        return encryptionService.getEncFrontend().getPaciente().encriptar(paciente);
    }

    public List<Paciente> findAll() {

        List<Paciente> pacientes = this.repository.findAll();
            for (int i = 0; i < pacientes.size(); i++) {
                Paciente paciente = encryptionService.getEncBackend().getPaciente().desencriptar(pacientes.get(i));
                paciente = encryptionService.getEncFrontend().getPaciente().encriptar(paciente);
                pacientes.set(i, paciente);
            }
            return pacientes;
    }

    public Paciente findByCedula(Paciente paciente) {
        paciente=encryptionService.getEncFrontend().getPaciente().desencriptar(paciente);
        paciente=encryptionService.getEncBackend().getPaciente().encriptar(paciente);
        paciente = this.repository.findByCedula(paciente.getCedula());

        paciente = encryptionService.getEncBackend().getPaciente().desencriptar(paciente);
        return encryptionService.getEncFrontend().getPaciente().encriptar(paciente);
    }


    @Transactional
    public void actualizarDatosPaciente(Paciente pac) {
        pac = encryptionService.getEncFrontend().getPaciente().desencriptar(pac);
        pac = encryptionService.getEncBackend().getPaciente().encriptar(pac);
        this.repository.save(pac);
    }

    public List<Paciente> findPacientesActivos() {
        List<Paciente> pacientes = this.repository.findPacientesActivos();
            for (int i = 0; i < pacientes.size(); i++) {
                Paciente paciente = encryptionService.getEncBackend().getPaciente().desencriptar(pacientes.get(i));
                paciente = encryptionService.getEncFrontend().getPaciente().encriptar(paciente);
                pacientes.set(i, paciente);
            }
            return pacientes;
    }

    public List<Paciente> findPacientesInactivos(String cedula) {
        Paciente pac = new Paciente(cedula);
        pac = encryptionService.getEncFrontend().getPaciente().desencriptar(pac);
        pac = encryptionService.getEncBackend().getPaciente().encriptar(pac);
        List<Paciente> pacientes = this.repository.findPacientesInactivos(pac.getCedula());
            for (int i = 0; i < pacientes.size(); i++) {
                Paciente paciente = encryptionService.getEncBackend().getPaciente().desencriptar(pacientes.get(i));
                paciente = encryptionService.getEncFrontend().getPaciente().encriptar(paciente);
                pacientes.set(i, paciente);
            }
            return pacientes;

    }

    public List<Object[]> findAllPacientesDatos() {
        List<Object[]> pacientes = this.repository.findAllPacientesDatos();
            for (int i = 0; i < pacientes.size(); i++) {
                Object[] pacienteArray = pacientes.get(i);
                if (pacienteArray != null && pacienteArray.length > 0 && pacienteArray[0] instanceof Paciente) {
                    Paciente paciente = (Paciente) pacienteArray[0];
                    paciente = encryptionService.getEncBackend().getPaciente().desencriptar(paciente);
                    paciente = encryptionService.getEncFrontend().getPaciente().encriptar(paciente);
                    pacienteArray[0] = paciente;
                    pacientes.add(pacienteArray);
                }
            }
            return pacientes;
    }
}