package com.dialisis.dialisisperitoneal.service.encryption.servicesEncryEntity;

import com.dialisis.dialisisperitoneal.persistence.entity.Alergia;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.apache.commons.codec.binary.Base64.encodeBase64;

public class EncryptionServiceAlergia {
    private String iv ;
    private String clave;

    public EncryptionServiceAlergia(String iv, String clave) {
        this.iv = iv;
        this.clave = clave;
    }

    public Alergia desencriptar(Alergia alergia) {
        try {
            if(alergia==null)return null;

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            if(alergia.getNombre()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(alergia.getNombre()));
                alergia.setNombre(new String(nombreDesencriptadoBytes));
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return alergia;
    }

    public Alergia encriptar(Alergia alergia){
        try {
            if(alergia==null)return null;
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            if(alergia.getNombre()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(alergia.getNombre().getBytes());
                alergia.setNombre(new String(encodeBase64(nombreDesencriptadoBytes)));
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return alergia;
    }

}