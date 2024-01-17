package com.dialisis.dialisisperitoneal.service.encryption.servicesEncryEntity;

import com.dialisis.dialisisperitoneal.persistence.entity.FormulaMedicamento;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.apache.commons.codec.binary.Base64.encodeBase64;

public class EncryptionServiceFormulaMedicamento {
    private String iv ;
    private String clave;
    private EncryptionServicePaciente encryptionServicePaciente;
    public EncryptionServiceFormulaMedicamento(String iv, String clave) {
        this.iv = iv;
        this.clave = clave;
        this.encryptionServicePaciente=new EncryptionServicePaciente(iv,clave);
    }
    public FormulaMedicamento desencriptar(FormulaMedicamento formulaMedicamento) {
        try {
            if(formulaMedicamento==null)return null;
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            if(formulaMedicamento.getTomas()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(formulaMedicamento.getTomas()));
                formulaMedicamento.setTomas(new String(nombreDesencriptadoBytes));
            }
            if(formulaMedicamento.getConcentracion()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(formulaMedicamento.getConcentracion()));
                formulaMedicamento.setConcentracion(new String(nombreDesencriptadoBytes));
            }
            if(formulaMedicamento.getDescripcion()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(formulaMedicamento.getDescripcion()));
                formulaMedicamento.setDescripcion(new String(nombreDesencriptadoBytes));
            }
            if(formulaMedicamento.getNombre()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(formulaMedicamento.getNombre()));
                formulaMedicamento.setNombre(new String(nombreDesencriptadoBytes));
            }
            if(formulaMedicamento.getTomas()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(formulaMedicamento.getTomas()));
                formulaMedicamento.setTomas(new String(nombreDesencriptadoBytes));
            }
            if(formulaMedicamento.getPaciente()!=null) {
                formulaMedicamento.setPaciente(encryptionServicePaciente.desencriptar(formulaMedicamento.getPaciente()));
            }

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return formulaMedicamento;
    }

    public FormulaMedicamento encriptar(FormulaMedicamento formulaMedicamento){
        try {
            if(formulaMedicamento==null)return null;
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            if(formulaMedicamento.getTomas()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(formulaMedicamento.getTomas().getBytes());
                formulaMedicamento.setTomas(new String(encodeBase64(nombreDesencriptadoBytes)));
            }
            if(formulaMedicamento.getConcentracion()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(formulaMedicamento.getConcentracion().getBytes());
                formulaMedicamento.setConcentracion(new String(encodeBase64(nombreDesencriptadoBytes)));
            }
            if(formulaMedicamento.getDescripcion()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(formulaMedicamento.getDescripcion().getBytes());
                formulaMedicamento.setDescripcion(new String(encodeBase64(nombreDesencriptadoBytes)));
            }
            if(formulaMedicamento.getNombre()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(formulaMedicamento.getNombre().getBytes());
                formulaMedicamento.setNombre(new String(encodeBase64(nombreDesencriptadoBytes)));
            }
            if(formulaMedicamento.getPaciente()!=null) {
                formulaMedicamento.setPaciente(encryptionServicePaciente.encriptar(formulaMedicamento.getPaciente()));
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return formulaMedicamento;
    }
}
