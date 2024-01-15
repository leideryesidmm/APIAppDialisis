package com.dialisis.dialisisperitoneal.service.encryption;

import com.dialisis.dialisisperitoneal.persistence.entity.Usuario;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;
import java.util.List;

@Service
public class EncryptionServiceBackend {
    private static final String cajaNegra2 = "y362_02j=27w/=42";
    String iv2="bs72/=a12dx=o=pl";

    public EncryptionServiceBackend() {
    }

    public List<Usuario> desencriptarAdminsBackend(List<Usuario> usuarios) {

        try{
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // Configura la clave y el vector de inicialización (iv)
            SecretKeySpec secretKeySpec = new SecretKeySpec(cajaNegra2.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv2.getBytes(StandardCharsets.UTF_8));

            // Inicializa el cifrado en modo descifrado
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);


            for (Usuario usuario:usuarios) {
                byte[] cedulaDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(usuario.getCedula()));
                byte[] contraseniaDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(usuario.getContrasenia()));
                byte[] celularDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(usuario.getCelular()));
                byte[] correoDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(usuario.getCorreo()));
                byte[] tipoDocumentoDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(usuario.getTipoDocumento()));
                byte[] nombreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(usuario.getNombre()));
                usuario.setCedula(new String(cedulaDesencriptadoBytes));
                usuario.setContrasenia(new String (contraseniaDesencriptadoBytes));
                usuario.setCelular(new String (celularDesencriptadoBytes));
                usuario.setCorreo(new String (correoDesencriptadoBytes));
                usuario.setTipoDocumento(new String (tipoDocumentoDesencriptadoBytes));
                usuario.setNombre(new String (nombreDesencriptadoBytes));
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return usuarios;
    }



}