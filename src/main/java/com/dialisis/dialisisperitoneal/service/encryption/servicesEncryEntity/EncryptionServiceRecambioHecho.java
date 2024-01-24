package com.dialisis.dialisisperitoneal.service.encryption.servicesEncryEntity;

import com.dialisis.dialisisperitoneal.persistence.entity.Recambio;
import com.dialisis.dialisisperitoneal.persistence.entity.RecambioHecho;
import com.dialisis.dialisisperitoneal.service.encryption.servicesEncryEntity.EncryptionServiceRecambio;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.apache.commons.codec.binary.Base64.encodeBase64;



public class EncryptionServiceRecambioHecho {
    private EncryptionServiceRecambio encryptionServiceRecambio;
    private String clave;
    private String iv;

    public EncryptionServiceRecambioHecho(String iv, String clave) {
        this.encryptionServiceRecambio = new EncryptionServiceRecambio(iv, clave);
        this.clave = clave;
        this.iv = iv;
    }

    public RecambioHecho encriptar(RecambioHecho recambioHecho){
        try {
            if(recambioHecho==null)return null;
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            if (recambioHecho.getDrenajeDialisis() != null) {
                byte[] drenajeEncriptadoBytes = cipher.doFinal(recambioHecho.getDrenajeDialisis().getBytes());
                recambioHecho.setDrenajeDialisis(new String(encodeBase64(drenajeEncriptadoBytes)));
            }
            if(recambioHecho.getOrificioSalida()!=null) {
                byte[] orificioDesencriptadoBytes = cipher.doFinal(recambioHecho.getOrificioSalida().getBytes());
                recambioHecho.setOrificioSalida(new String(encodeBase64(orificioDesencriptadoBytes)));
            }
            if(recambioHecho.getCaracteristicaLiquido()!=null){
                byte[] caracteristicaLiquidoDesencriptadoBytes = cipher.doFinal(recambioHecho.getCaracteristicaLiquido().getBytes());
                recambioHecho.setCaracteristicaLiquido(new String(encodeBase64(caracteristicaLiquidoDesencriptadoBytes)));
            }
            if(recambioHecho.getLiquidoEntrante()!=null) {
                byte[] liquidoDesencriptadoBytes = cipher.doFinal(recambioHecho.getLiquidoEntrante().getBytes());
                recambioHecho.setLiquidoEntrante(new String(encodeBase64(liquidoDesencriptadoBytes)));
            }
            if(recambioHecho.getRecambio()!=null) {
                Recambio recambio= new Recambio(recambioHecho.getRecambio());
                recambioHecho.setRecambio(encryptionServiceRecambio.encriptar(recambio));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return recambioHecho;
    }
    public RecambioHecho desencriptar(RecambioHecho recambioHecho){
        try {
            if(recambioHecho==null)return null;
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            if (recambioHecho.getDrenajeDialisis() != null) {
                byte[] drenajeDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(recambioHecho.getDrenajeDialisis()));
                recambioHecho.setDrenajeDialisis(new String(drenajeDesencriptadoBytes));
            }
            if(recambioHecho.getOrificioSalida()!=null) {
                byte[] orificioDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(recambioHecho.getOrificioSalida()));
                recambioHecho.setOrificioSalida(new String(orificioDesencriptadoBytes));
            }
            if(recambioHecho.getCaracteristicaLiquido()!=null){
                byte[] caracteristicaLiquidoDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(recambioHecho.getCaracteristicaLiquido()));
                recambioHecho.setCaracteristicaLiquido(new String(caracteristicaLiquidoDesencriptadoBytes));
            }
            if(recambioHecho.getLiquidoEntrante()!=null) {
                byte[] liquidoDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(recambioHecho.getLiquidoEntrante()));
                recambioHecho.setLiquidoEntrante(new String(liquidoDesencriptadoBytes));
            }
            if(recambioHecho.getRecambio()!=null) {
                Recambio recambio= new Recambio(recambioHecho.getRecambio());
                recambioHecho.setRecambio(encryptionServiceRecambio.desencriptar(recambio));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return recambioHecho;
    }
}