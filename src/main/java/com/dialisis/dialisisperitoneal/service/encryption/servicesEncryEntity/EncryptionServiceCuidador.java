package com.dialisis.dialisisperitoneal.service.encryption.servicesEncryEntity;

import com.dialisis.dialisisperitoneal.persistence.entity.Cuidador;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.apache.commons.codec.binary.Base64.encodeBase64;

public class EncryptionServiceCuidador {
    private String iv ;
    private String clave;

    public EncryptionServiceCuidador(String iv, String clave) {
        this.iv = iv;
        this.clave = clave;
    }

    public Cuidador desencriptar(Cuidador cuidador) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // Configura la clave y el vector de inicialización (iv)
            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            // Inicializa el cifrado en modo descifrado
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            if(cuidador.getCedulaCuidador()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(cuidador.getCedulaCuidador()));
                cuidador.setCedulaCuidador(new String(nombreDesencriptadoBytes));
            }
            if(cuidador.getNombre()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(cuidador.getNombre()));
                cuidador.setNombre(new String(nombreDesencriptadoBytes));
            }
            if(cuidador.getDireccion()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(cuidador.getDireccion()));
                cuidador.setDireccion(new String(nombreDesencriptadoBytes));
            }
            if(cuidador.getTelefono()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(cuidador.getTelefono()));
                cuidador.setTelefono(new String(nombreDesencriptadoBytes));
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return cuidador;
    }

    public Cuidador encriptar(Cuidador cuidador){
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            // Inicializa el cifrado en modo descifrado
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            if(cuidador.getCedulaCuidador()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(cuidador.getCedulaCuidador().getBytes());
                cuidador.setCedulaCuidador(new String(encodeBase64(nombreDesencriptadoBytes)));
            }
            if(cuidador.getNombre()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(cuidador.getNombre().getBytes());
                cuidador.setNombre(new String(encodeBase64(nombreDesencriptadoBytes)));
            }
            if(cuidador.getDireccion()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(cuidador.getDireccion().getBytes());
                cuidador.setDireccion(new String(encodeBase64(nombreDesencriptadoBytes)));
            }
            if(cuidador.getTelefono()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(cuidador.getTelefono().getBytes());
                cuidador.setTelefono(new String(encodeBase64(nombreDesencriptadoBytes)));
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return cuidador;
    }


}
