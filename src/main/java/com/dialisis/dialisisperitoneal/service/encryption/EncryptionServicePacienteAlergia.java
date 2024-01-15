package com.dialisis.dialisisperitoneal.service.encryption;
import com.dialisis.dialisisperitoneal.persistence.entity.PacienteAlergia;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

import static org.apache.commons.codec.binary.Base64.encodeBase64;

@Service
public class EncryptionServicePacienteAlergia {

    private EncryptionServicePaciente encryptionServicePaciente;
    private EncryptionServiceAlergia encryptionServiceAlergia;
    private  String clave;
    private  String iv;

    public EncryptionServicePacienteAlergia(String iv, String clave) {
        this.encryptionServicePaciente = new EncryptionServicePaciente(clave, iv);
        this.encryptionServiceAlergia = new EncryptionServiceAlergia(iv, clave);
        this.clave = clave;
        this.iv = iv;
    }
    public PacienteAlergia encriptar(PacienteAlergia pacienteAlergia){

        try {
            System.out.println("Recambio del form" + pacienteAlergia);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            if (pacienteAlergia.getPaciente() != null) {
                pacienteAlergia.setPaciente(encryptionServicePaciente.encriptar(pacienteAlergia.getPaciente()));
            }
            if (pacienteAlergia.getPaciente() != null) {
                pacienteAlergia.setPaciente(encryptionServiceAlergia.encriptar(pacienteAlergia.getAlergia()));
            }
            System.out.println("nuevo usuario "+pacienteAlergia);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return pacienteAlergia;
    }
    public PacienteAlergia desencriptar(PacienteAlergia pacienteAlergia){
        try {
            System.out.println("usuario del form" + pacienteAlergia);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // Configura la clave y el vector de inicialización (IV)
            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            // Inicializa el cifrado en modo cifrado
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            if (pacienteAlergia.getPaciente() != null) {
                pacienteAlergia.setPaciente(encryptionServicePaciente.desencriptar(pacienteAlergia.getPaciente()));
            }
            if (pacienteAlergia.getPaciente() != null) {
                pacienteAlergia.setPaciente(encryptionServiceAlergia.desencriptar(pacienteAlergia.getAlergia()));
            }

            System.out.println("nuevo usuario "+pacienteAlergia);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return pacienteAlergia;
    }
}
