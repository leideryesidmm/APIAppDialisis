package com.dialisis.dialisisperitoneal.service.encryption.servicesEncryEntity;
import com.dialisis.dialisisperitoneal.persistence.entity.Alergia;
import com.dialisis.dialisisperitoneal.persistence.entity.Paciente;
import com.dialisis.dialisisperitoneal.persistence.entity.PacienteAlergia;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

import static org.apache.commons.codec.binary.Base64.encodeBase64;

public class EncryptionServicePacienteAlergia {

    private EncryptionServicePaciente encryptionServicePaciente;
    private EncryptionServiceAlergia encryptionServiceAlergia;
    private String clave;
    private String iv;

    public EncryptionServicePacienteAlergia(String iv, String clave) {
        this.encryptionServicePaciente = new EncryptionServicePaciente(iv, clave);
        this.encryptionServiceAlergia = new EncryptionServiceAlergia(iv, clave);
        this.clave = clave;
        this.iv = iv;
    }
    public PacienteAlergia encriptar(PacienteAlergia pacienteAlergia){
        try {
            if(pacienteAlergia==null)return null;
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            if (pacienteAlergia.getPaciente() != null) {
                Paciente paciente=new Paciente(pacienteAlergia.getPaciente());
                pacienteAlergia.setPaciente(encryptionServicePaciente.encriptar(paciente));
            }
            if (pacienteAlergia.getAlergia() != null) {
                Alergia alergia= new Alergia(pacienteAlergia.getAlergia());
                pacienteAlergia.setAlergia(encryptionServiceAlergia.encriptar(alergia));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return pacienteAlergia;
    }
    public PacienteAlergia desencriptar(PacienteAlergia pacienteAlergia){
        try {
            if(pacienteAlergia==null)return null;
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            if (pacienteAlergia.getPaciente() != null) {
                Paciente paciente=new Paciente(pacienteAlergia.getPaciente());
                pacienteAlergia.setPaciente(encryptionServicePaciente.desencriptar(paciente));
            }
            if (pacienteAlergia.getAlergia() != null) {
                pacienteAlergia.setAlergia(encryptionServiceAlergia.desencriptar(pacienteAlergia.getAlergia()));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return pacienteAlergia;
    }
}
