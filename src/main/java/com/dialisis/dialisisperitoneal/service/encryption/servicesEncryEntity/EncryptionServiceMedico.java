package com.dialisis.dialisisperitoneal.service.encryption.servicesEncryEntity;

import com.dialisis.dialisisperitoneal.persistence.entity.Medico;
import com.dialisis.dialisisperitoneal.persistence.entity.RecambioHecho;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.apache.commons.codec.binary.Base64.encodeBase64;

public class EncryptionServiceMedico {

    private String clave;
    private String iv;

    public EncryptionServiceMedico(String iv, String clave) {

        this.clave = clave;
        this.iv = iv;
    }

    public Medico encriptar(Medico medico){
        try {
            if(medico==null)return null;
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            if (medico.getCedula() != null) {
                byte[] cedulaEncriptadoBytes = cipher.doFinal(medico.getCedula().getBytes());
                medico.setCedula(new String(encodeBase64(cedulaEncriptadoBytes)));
            }
            if(medico.getProfesion()!=null) {
                byte[] profesionDesencriptadoBytes = cipher.doFinal(medico.getProfesion().getBytes());
                medico.setProfesion(new String(encodeBase64(profesionDesencriptadoBytes)));
            }
            if(medico.getContrasenia()!=null){
                byte[] contraseniaEncriptada = cipher.doFinal(medico.getContrasenia().getBytes());
                medico.setContrasenia(new String(encodeBase64(contraseniaEncriptada)));}
            if(medico.getCelular()!=null){
                byte[] celularEncriptado = cipher.doFinal(medico.getCelular().getBytes());
                medico.setCelular(new String(encodeBase64(celularEncriptado)));}
            if(medico.getNombre()!=null){
                byte[] nombreEncriptado = cipher.doFinal(medico.getNombre().getBytes());
                medico.setNombre(new String(encodeBase64(nombreEncriptado)));}
            if(medico.getCorreo()!=null){
                byte[] correoEncriptado = cipher.doFinal(medico.getCorreo().getBytes());
                medico.setCorreo(new String(encodeBase64(correoEncriptado)));}
            if(medico.getTipoDocumento()!=null){
                byte[] tipoDocumentoEncriptado = cipher.doFinal(medico.getTipoDocumento().getBytes());
                medico.setTipoDocumento(new String(encodeBase64(tipoDocumentoEncriptado)));}
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return medico;
    }
    public Medico desencriptar(Medico medico){
        try {

            if(medico==null)return null;
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            if (medico.getCedula() != null) {
                byte[] cedulaDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(medico.getCedula()));
                medico.setCedula(new String(cedulaDesencriptadoBytes));
            }

            if(medico.getProfesion()!=null) {
                byte[] profesionDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(medico.getProfesion()));
                medico.setProfesion(new String(profesionDesencriptadoBytes));
            }
            if(medico.getContrasenia()!=null){
                byte[] contraseniaDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(medico.getContrasenia()));
                medico.setContrasenia(new String (contraseniaDesencriptadoBytes));}
            if(medico.getCelular()!=null){
                byte[] celularDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(medico.getCelular()));
                medico.setCelular(new String (celularDesencriptadoBytes));}
            if(medico.getCorreo()!=null){
                byte[] correoDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(medico.getCorreo()));
                medico.setCorreo(new String (correoDesencriptadoBytes));}
            if(medico.getTipoDocumento()!=null){
                byte[] tipoDocumentoDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(medico.getTipoDocumento()));
                medico.setTipoDocumento(new String (tipoDocumentoDesencriptadoBytes));}
            if(medico.getNombre()!=null){
                byte[] nombreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(medico.getNombre()));
                medico.setNombre(new String (nombreDesencriptadoBytes));}
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return medico;
    }
}
