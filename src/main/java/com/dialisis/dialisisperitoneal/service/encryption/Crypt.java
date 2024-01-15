package com.dialisis.dialisisperitoneal.service.encryption;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
import java.util.Base64;

public class Crypt {
    public static void main(String[] args) {
        // Registrar Bouncy Castle como proveedor de seguridad
        Security.addProvider(new BouncyCastleProvider());

        String encryptedText = "U2FsdGVkX1/Oz9pRJmljUkqOqfr2tytEvZDEHQYMPrY=";
        String password = "clave_secreta123";

        try {
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);

            // Usar la clave directamente sin derivación
            byte[] keyBytes = password.getBytes("UTF-8");
            SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");

            // Configurar el cifrado con el mismo algoritmo y el modo que usa CryptoJS
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding", "BC");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // Descifrar el mensaje
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            // Convertir los bytes descifrados a una cadena
            String decryptedText = new String(decryptedBytes);
            System.out.println("Mensaje descifrado: " + decryptedText.trim());  // Trim para eliminar el relleno no deseado
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error de desencriptación: " + e.getMessage());
        }
    }
}
