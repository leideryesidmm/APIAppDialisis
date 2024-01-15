package com.dialisis.dialisisperitoneal.service.encryption;

import com.dialisis.dialisisperitoneal.persistence.entity.Usuario;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.Base64.encodeBase64;

@Service
public class EncryptionServiceFrontend {
    private static final String frontend = "vfjdnjefh37/ps=3";
    String iv = "vuens_soha=6jh36";
    public Usuario desencriptarAdminFrontend(Usuario usuario){
        try {
            System.out.println("usuario del form"+usuario);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // Configura la clave y el vector de inicialización (IV)
            SecretKeySpec secretKeySpec = new SecretKeySpec(frontend.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            // Inicializa el cifrado en modo cifrado
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] cedulaDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(usuario.getCedula()));
            byte[] contraseniaDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(usuario.getContrasenia()));
            usuario.setCedula(new String(cedulaDesencriptadoBytes, StandardCharsets.UTF_8));
            usuario.setContrasenia(new String(contraseniaDesencriptadoBytes, StandardCharsets.UTF_8));
            System.out.println("nuevo usuario "+usuario);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return usuario;
    }

    public Usuario encriptarAdminFrontend(Usuario usuario) {
        try {
           System.out.println("encriptado admin frontend hbwydbyuwedbhej");
            System.out.println(usuario);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // Configura la clave y el vector de inicialización (iv)
            SecretKeySpec secretKeySpec = new SecretKeySpec(frontend.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            // Inicializa el cifrado en modo cifrado
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] cedulaEncriptada = cipher.doFinal(usuario.getCedula().getBytes());
            usuario.setCedula(new String(encodeBase64(cedulaEncriptada)));
            byte[] contraseniaEncriptada = cipher.doFinal(usuario.getContrasenia().getBytes());
            usuario.setContrasenia(new String(encodeBase64(contraseniaEncriptada)));
            byte[] celularEncriptado = cipher.doFinal(usuario.getCelular().getBytes());
            usuario.setCelular(new String(encodeBase64(celularEncriptado)));
            byte[] nombreEncriptado = cipher.doFinal(usuario.getNombre().getBytes());
            usuario.setNombre(new String(encodeBase64(nombreEncriptado)));
            byte[] correoEncriptado = cipher.doFinal(usuario.getCorreo().getBytes());
            usuario.setCorreo(new String(encodeBase64(correoEncriptado)));
            byte[] tipoDocumentoEncriptado = cipher.doFinal(usuario.getTipoDocumento().getBytes());
            usuario.setTipoDocumento(new String(encodeBase64(tipoDocumentoEncriptado)));



            System.out.println("encriptando para el frontend");
            System.out.println(usuario);
            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    }

