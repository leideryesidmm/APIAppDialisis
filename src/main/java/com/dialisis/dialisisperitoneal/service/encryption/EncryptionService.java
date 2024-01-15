package com.dialisis.dialisisperitoneal.service.encryption;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class EncryptionService {
    public static void whenIsEncryptedAndDecrypted_thenDecryptedEqualsOriginal() {
        try {
            String encryptionKeyString = "clave_secreta123";
            String originalMessage = "U2FsdGVkX1/5h6Q5b9YKyEJ6bWuK8IRw3eS5bcmXrQ4=";

            // Decodificar el mensaje cifrado desde Base64
            byte[] encryptedMessageBytes = Base64.getDecoder().decode(originalMessage);

            // Extraer el IV (Initialization Vector) de los primeros 16 bytes del mensaje cifrado
            byte[] ivBytes = new byte[16];
            System.arraycopy(encryptedMessageBytes, 0, ivBytes, 0, 16);

            // Resto del mensaje sin el IV
            byte[] cipherTextBytes = new byte[encryptedMessageBytes.length - 16];
            System.arraycopy(encryptedMessageBytes, 16, cipherTextBytes, 0, cipherTextBytes.length);

            // Crear el objeto IvParameterSpec con el IV
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);

            // Crear la clave secreta
            SecretKey secretKey = new SecretKeySpec(encryptionKeyString.getBytes(StandardCharsets.UTF_8), "AES");

            // Configurar el cifrado en modo CBC con PKCS5Padding
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);

            // Descifrar el mensaje
            byte[] decryptedMessageBytes = cipher.doFinal(cipherTextBytes);

            // Convertir los bytes descifrados a una cadena
            String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);
            System.out.println("Mensaje descifrado: " + decryptedMessage);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error de desencriptación: " + e.getMessage());
        }
    }


    public static void main(String [] args){
        whenIsEncryptedAndDecrypted_thenDecryptedEqualsOriginal();
    }
}