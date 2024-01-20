package com.dialisis.dialisisperitoneal.service.encryption.servicesEncryEntity;

import com.dialisis.dialisisperitoneal.persistence.entity.PrescripcionDia;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class EncryptionServicePrescripcionDia {

    private String clave;
    private String iv;
    private EncryptionServiceCita encryptionServiceCita;

    public EncryptionServicePrescripcionDia(String iv, String clave) {
        this.encryptionServiceCita = new EncryptionServiceCita(iv, clave);
        this.clave = clave;
        this.iv = iv;
    }
    public PrescripcionDia encriptar(PrescripcionDia prescripcionDia){
        try {
            if(prescripcionDia==null)return null;
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            if (prescripcionDia.getCita() != null) {
                prescripcionDia.setCita(encryptionServiceCita.encriptar(prescripcionDia.getCita()));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return prescripcionDia;
    }
    public PrescripcionDia desencriptar(PrescripcionDia prescripcionDia){
        try {
            if(prescripcionDia==null)return null;
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            if (prescripcionDia.getCita() != null) {
                prescripcionDia.setCita(encryptionServiceCita.desencriptar(prescripcionDia.getCita()));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return prescripcionDia;
    }
}
