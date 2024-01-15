package com.dialisis.dialisisperitoneal.service.encryption;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class EncryptionServiceFrontend {

    private final String clave = "vsjki526an#%cdsk";
    private final String iv="d3r56dcdve3$fds-";
    private final EncryptServiceAdmin admin;
    private final EncryptionServiceMedico medico;
    private final EncryptionServicePaciente paciente;
    private final EncryptionServicePacienteAlergia pacienteAlergia;
    private final EncryptionServiceRecambio recambio;
    private final EncryptionServiceRecambioHecho recambioHecho;


    public EncryptionServiceFrontend(EncryptServiceAdmin admin, EncryptionServiceMedico medico, EncryptionServicePaciente paciente, EncryptionServicePacienteAlergia pacienteAlergia, EncryptionServiceRecambio recambio, EncryptionServiceRecambioHecho recambioHecho) {
        this.admin = new EncryptServiceAdmin(iv, clave);
        this.alergia = new EncryptionServiceAlergia(iv, clave);
        this.chequeo = new EncryptionServiceChequeo(iv, clave);
        this.cita = new EncryptionServiceCita(iv, clave);
        this.cuidador = new EncryptionServiceCuidador(iv,clave);
        this.cuidadorPaciente = new EncryptionServiceCuidadorPaciente(iv, clave);
        this.formulaMedicamento = new EncryptionServiceFormulaMedicamento(iv, clave);
        this.paciente = new EncryptionServicePaciente(iv, clave);
        this.medico = new EncryptionServiceMedico(iv, clave);
        this.paciente = new EncryptionServicePaciente(iv, clave);
        this.pacienteAlergia = new EncryptionServicePacienteAlergia(iv, clave);
        this.recambio = new EncryptionServiceRecambio(iv, clave);
        this.recambioHecho = new EncryptionServiceRecambioHecho(iv, clave);
    }
}
