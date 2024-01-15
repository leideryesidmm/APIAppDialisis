package com.dialisis.dialisisperitoneal.service.encryption;

import com.dialisis.dialisisperitoneal.persistence.entity.Usuario;
import org.springframework.stereotype.Service;
import javax.crypto.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.util.Base64;
import java.util.List;

@Service
public class EncryptionServiceBackend {
    private static final String cajaNegra2 = "clavesecreta1234";
    private static final String frontend = "clavesecreta123";
    String iv = "vector_inicializ";

    public EncryptionServiceBackend() {
    }

    public List<Usuario> desencriptarAdminsBackend(List<Usuario> usuarios) {
        /*try {
            System.out.println("entro al try");

            SecretKeySpec keySpec = new SecretKeySpec(cajaNegra2.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            System.out.println("hizo la instancia del cipher");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            System.out.println("inicio el cipher");
            for (Usuario usuario : usuarios) {
                System.out.println("Paso el for");
                byte[] cedulaDesencriptada = cipher.doFinal(parse(usuario.getCedula()));
                usuario.setCedula(new String(cedulaDesencriptada, StandardCharsets.UTF_8));
                /*usuario.setCedula(new String(cipher.doFinal(Base64.getDecoder().decode(usuario.getCedula().getBytes()))));
                usuario.setContrasenia(new String(cipher.doFinal(Base64.getDecoder().decode(usuario.getContrasenia().getBytes()))));
                usuario.setCelular(new String(cipher.doFinal(Base64.getDecoder().decode(usuario.getCelular().getBytes()))));
                usuario.setCorreo(new String(cipher.doFinal(Base64.getDecoder().decode(usuario.getCorreo().getBytes()))));
                usuario.setNombre(new String(cipher.doFinal(Base64.getDecoder().decode(usuario.getNombre().getBytes()))));
                usuario.setTipoDocumento(new String(cipher.doFinal(Base64.getDecoder().decode(usuario.getTipoDocumento().getBytes()))));
        }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error de desencriptación: " + e.getMessage());
            return null;
        }

        return usuarios;*/
        /*try {
            String texto="U2FsdGVkX18y9aSIXx0/dtF6jO0ksYJgB7EaDBkRvFk=";
            byte[] claveBytes = cajaNegra2.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec secretKeySpec = new SecretKeySpec(claveBytes, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            byte[] textoCifradoBytes = Base64.getDecoder().decode(texto.getBytes());
            byte[] textoDesencriptadoBytes = cipher.doFinal(textoCifradoBytes);
            System.out.println("antes de desencriptar texto");
            System.out.println(new String(textoDesencriptadoBytes, StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }*/
        try{
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // Configura la clave y el vector de inicialización (iv)
            SecretKeySpec secretKeySpec = new SecretKeySpec(cajaNegra2.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            // Inicializa el cifrado en modo descifrado
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            String texto="i33oISEJN05iooS4hcH/VQ==";

            for (Usuario usuario:usuarios) {
                byte[] textoDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(texto));
                String textoDesencriptado= new String(textoDesencriptadoBytes, StandardCharsets.UTF_8);
                System.out.println("Texto desencriptado: "+ textoDesencriptado);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return usuarios;
    }



}

    /*private static Key generateKey(String secret) throws Exception {
        System.out.println("Entro al generate");
        byte[] decoded = Base64.getDecoder().decode(secret.getBytes());
        System.out.println("creo el decoded");
        Key key = new SecretKeySpec(decoded, "AES");
        System.out.println("genero el key");
        return key;
    }*/