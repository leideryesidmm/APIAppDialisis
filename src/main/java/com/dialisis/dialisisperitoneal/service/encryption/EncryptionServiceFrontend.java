package com.dialisis.dialisisperitoneal.service.encryption;

import com.dialisis.dialisisperitoneal.service.encryption.servicesEncryEntity.*;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class EncryptionServiceFrontend {
    private final String clave = "vfjdnjefh37/ps=3";
    private final String iv="vuens_soha=6jh36";
    private final EncryptServiceAdmin admin;
    private EncryptionServiceAlergia alergia;
    private EncryptionServiceChequeo chequeo;
    private EncryptionServiceCita cita;
    private EncryptionServiceCuidador cuidador;
    private EncryptionServiceCuidadorPaciente cuidadorPaciente;
    private EncryptionServiceFormulaMedicamento formulaMedicamento;
    private final EncryptionServiceMedico medico;
    private final EncryptionServicePaciente paciente;
    private final EncryptionServicePacienteAlergia pacienteAlergia;
    private final EncryptionServiceRecambio recambio;
    private final EncryptionServiceRecambioHecho recambioHecho;
    private final EncryptionServicePrescripcionDia prescripcionDia;
    private final EncryptionServiceVisitaEspecialistas visitaEspecialistas;

    public EncryptionServiceFrontend() {
        this.prescripcionDia = new EncryptionServicePrescripcionDia(iv, clave);
        this.visitaEspecialistas = new EncryptionServiceVisitaEspecialistas(iv, clave);
        this.admin = new EncryptServiceAdmin(iv, clave);
        this.alergia = new EncryptionServiceAlergia(iv, clave);
        this.chequeo = new EncryptionServiceChequeo(iv, clave);
        this.cita = new EncryptionServiceCita(iv, clave);
        this.cuidador = new EncryptionServiceCuidador(iv,clave);
        this.cuidadorPaciente = new EncryptionServiceCuidadorPaciente(iv, clave);
        this.formulaMedicamento = new EncryptionServiceFormulaMedicamento(iv, clave);
        this.medico = new EncryptionServiceMedico(iv, clave);
        this.paciente = new EncryptionServicePaciente(iv, clave);
        this.pacienteAlergia = new EncryptionServicePacienteAlergia(iv, clave);
        this.recambio = new EncryptionServiceRecambio(iv, clave);
        this.recambioHecho = new EncryptionServiceRecambioHecho(iv, clave);
    }
}