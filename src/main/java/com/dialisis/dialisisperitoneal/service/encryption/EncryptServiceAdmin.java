package com.dialisis.dialisisperitoneal.service.encryption;

import com.dialisis.dialisisperitoneal.persistence.entity.Usuario;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

import static org.apache.commons.codec.binary.Base64.encodeBase64;

@Service
public class EncryptServiceAdmin {


    private String clave;
    private String iv;

    public EncryptServiceAdmin(String clave, String iv) {
        this.clave = clave;
        this.iv = iv;
    }

    public Usuario desencriptar(Usuario usuario) {

        try{
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // Configura la clave y el vector de inicialización (iv)
            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            // Inicializa el cifrado en modo descifrado
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

                if(usuario.getCedula()!=null) {
                    byte[] cedulaDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(usuario.getCedula()));
                    usuario.setCedula(new String(cedulaDesencriptadoBytes, StandardCharsets.UTF_8));}
                if(usuario.getContrasenia()!=null){
                    byte[] contraseniaDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(usuario.getContrasenia()));
                    usuario.setContrasenia(new String (contraseniaDesencriptadoBytes, StandardCharsets.UTF_8));}
                if(usuario.getCelular()!=null){
                    byte[] celularDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(usuario.getCelular()));
                    usuario.setCelular(new String (celularDesencriptadoBytes, StandardCharsets.UTF_8));}
                if(usuario.getCorreo()!=null){
                    byte[] correoDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(usuario.getCorreo()));
                    usuario.setCorreo(new String (correoDesencriptadoBytes, StandardCharsets.UTF_8));}
                if(usuario.getTipoDocumento()!=null){
                    byte[] tipoDocumentoDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(usuario.getTipoDocumento()));
                    usuario.setTipoDocumento(new String (tipoDocumentoDesencriptadoBytes,StandardCharsets.UTF_8));}
                if(usuario.getNombre()!=null){
                    byte[] nombreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(usuario.getNombre()));
                    usuario.setNombre(new String (nombreDesencriptadoBytes, StandardCharsets.UTF_8));}
            }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return usuario;
    }

    public Usuario encriptar(Usuario usuario) {
        try {
            System.out.println("encriptado admin frontend hbwydbyuwedbhej");
            System.out.println(usuario);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // Configura la clave y el vector de inicialización (iv)
            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            // Inicializa el cifrado en modo cifrado
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            if(usuario.getCedula()!=null){
                byte[] cedulaEncriptada = cipher.doFinal(usuario.getCedula().getBytes());
                usuario.setCedula(new String(encodeBase64(cedulaEncriptada)));}
            if(usuario.getContrasenia()!=null){
                byte[] contraseniaEncriptada = cipher.doFinal(usuario.getContrasenia().getBytes());
                usuario.setContrasenia(new String(encodeBase64(contraseniaEncriptada)));}
            if(usuario.getCelular()!=null){
                byte[] celularEncriptado = cipher.doFinal(usuario.getCelular().getBytes());
                usuario.setCelular(new String(encodeBase64(celularEncriptado)));}
            if(usuario.getNombre()!=null){
                byte[] nombreEncriptado = cipher.doFinal(usuario.getNombre().getBytes());
                usuario.setNombre(new String(encodeBase64(nombreEncriptado)));}
            if(usuario.getCorreo()!=null){
                byte[] correoEncriptado = cipher.doFinal(usuario.getCorreo().getBytes());
                usuario.setCorreo(new String(encodeBase64(correoEncriptado)));}
            if(usuario.getTipoDocumento()!=null){
                byte[] tipoDocumentoEncriptado = cipher.doFinal(usuario.getTipoDocumento().getBytes());
                usuario.setTipoDocumento(new String(encodeBase64(tipoDocumentoEncriptado)));}



            System.out.println("encriptando para el frontend");
            System.out.println(usuario);
            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}