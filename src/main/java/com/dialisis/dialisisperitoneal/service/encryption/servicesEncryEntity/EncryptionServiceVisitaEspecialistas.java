package com.dialisis.dialisisperitoneal.service.encryption.servicesEncryEntity;

import com.dialisis.dialisisperitoneal.persistence.entity.Cita;
import com.dialisis.dialisisperitoneal.persistence.entity.PrescripcionDia;
import com.dialisis.dialisisperitoneal.persistence.entity.VisitaEspecialista;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class EncryptionServiceVisitaEspecialistas {

    private String clave;
    private String iv;
    private EncryptionServiceCita encryptionServiceCita;

    public EncryptionServiceVisitaEspecialistas(String iv, String clave) {
        this.encryptionServiceCita = new EncryptionServiceCita(clave, iv);
        this.clave = clave;
        this.iv = iv;
    }
    public VisitaEspecialista encriptar(VisitaEspecialista visitaEspecialista){
        try {
            if(visitaEspecialista==null)return null;
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            if (visitaEspecialista.getCita() != null) {
                Cita cita= new Cita(visitaEspecialista.getCita());
                visitaEspecialista.setCita(encryptionServiceCita.encriptar(cita));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return visitaEspecialista;
    }
    public VisitaEspecialista desencriptar(VisitaEspecialista visitaEspecialista){
        try {
            if(visitaEspecialista==null)return null;
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            if (visitaEspecialista.getCita() != null) {
                Cita cita= new Cita(visitaEspecialista.getCita());
                visitaEspecialista.setCita(encryptionServiceCita.desencriptar(cita));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return visitaEspecialista;
    }
}
