package com.dialisis.dialisisperitoneal.service.encryption.servicesEncryEntity;

import com.dialisis.dialisisperitoneal.persistence.entity.Recambio;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.apache.commons.codec.binary.Base64.encodeBase64;

public class EncryptionServiceRecambio {

    private EncryptionServicePrescripcionDia encryptionServicePrescripcionDia;
    private  String clave;
    private  String iv;

    public EncryptionServiceRecambio(String iv, String clave) {
        this.clave = clave;
        this.iv = iv;
        this.encryptionServicePrescripcionDia=new EncryptionServicePrescripcionDia(iv, clave);
    }

    public EncryptionServiceRecambio() {
    }

    public Recambio encriptar(Recambio recambio){
        try {
            if(recambio==null)return null;
            System.out.println("Recambio del form" + recambio);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            if (recambio.getConcentracion() != null) {
                byte[] concentracionEncriptadoBytes = cipher.doFinal(recambio.getConcentracion().getBytes());
                recambio.setConcentracion(new String(encodeBase64(concentracionEncriptadoBytes)));
            }
            if (recambio.getIntervaloTiempo() != null) {
                byte[] intervaloTimepoEncriptadoBytes = cipher.doFinal(recambio.getIntervaloTiempo().getBytes());
                recambio.setIntervaloTiempo(new String(encodeBase64(intervaloTimepoEncriptadoBytes)));
            }
            if(recambio.getPrescripcionDia()!=null) {
                recambio.setPrescripcionDia(encryptionServicePrescripcionDia.encriptar(recambio.getPrescripcionDia()));
            }
            System.out.println("nuevo usuario "+recambio);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return recambio;
    }

    public Recambio desencriptar(Recambio recambio){
        try {
            if(recambio==null)return null;
            System.out.println("usuario del form" + recambio);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            if (recambio.getConcentracion() != null) {
                byte[] concentracionDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(recambio.getConcentracion()));
                recambio.setConcentracion(new String(concentracionDesencriptadoBytes));
            }
            if(recambio.getIntervaloTiempo()!=null) {
                byte[] intervaloDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(recambio.getIntervaloTiempo()));
                recambio.setIntervaloTiempo(new String(intervaloDesencriptadoBytes));
            }
            if(recambio.getPrescripcionDia()!=null) {
                recambio.setPrescripcionDia(encryptionServicePrescripcionDia.desencriptar(recambio.getPrescripcionDia()));
            }
            System.out.println("nuevo usuario "+recambio);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return recambio;
    }

}
