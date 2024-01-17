package com.dialisis.dialisisperitoneal.service.encryption.servicesEncryEntity;

import com.dialisis.dialisisperitoneal.persistence.entity.Cita;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.apache.commons.codec.binary.Base64.encodeBase64;

public class EncryptionServiceCita {
    private String iv ;
    private String clave;

    private EncryptionServiceMedico encryptionServiceMedico;
    private EncryptionServicePaciente encryptionServicePaciente;

    public EncryptionServiceCita(String iv, String clave) {
        this.iv = iv;
        this.clave = clave;
        this.encryptionServiceMedico=new EncryptionServiceMedico(iv,clave);
        this.encryptionServicePaciente=new EncryptionServicePaciente(iv,clave);
    }

    public Cita desencriptar(Cita cita) {
        try {
            if(cita==null) return null;
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            if(cita.getMedico()!=null) {
               cita.setMedico(encryptionServiceMedico.desencriptar(cita.getMedico()));
            }
            if(cita.getPaciente()!=null) {
               cita.setPaciente(encryptionServicePaciente.desencriptar(cita.getPaciente()));
            }
            if(cita.getOrificioSalida()!=null){
                byte[] nombreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(cita.getOrificioSalida()));
                cita.setOrificioSalida(new String(nombreDesencriptadoBytes));
            }

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return cita;
    }

    public Cita encriptar(Cita cita){
        try {
            if(cita==null) return null;
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            if(cita.getMedico()!=null) {
                cita.setMedico(encryptionServiceMedico.encriptar(cita.getMedico()));
            }
            if(cita.getPaciente()!=null) {
                cita.setPaciente(encryptionServicePaciente.encriptar(cita.getPaciente()));
            }
            if(cita.getOrificioSalida()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(cita.getOrificioSalida().getBytes());
                cita.setOrificioSalida(new String(encodeBase64(nombreDesencriptadoBytes)));
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return cita;
    }

}
