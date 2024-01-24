package com.dialisis.dialisisperitoneal.service.encryption.servicesEncryEntity;

import com.dialisis.dialisisperitoneal.persistence.entity.Cuidador;
import com.dialisis.dialisisperitoneal.persistence.entity.CuidadorPaciente;
import com.dialisis.dialisisperitoneal.persistence.entity.Paciente;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;



public class EncryptionServiceCuidadorPaciente {
    private final String iv ;
    private final String clave;
    private EncryptionServiceCuidador encryptionServiceCuidador;
    private EncryptionServicePaciente encryptionServicePaciente;
    public EncryptionServiceCuidadorPaciente(String iv, String clave) {
        this.iv = iv;
        this.clave = clave;
        encryptionServiceCuidador= new EncryptionServiceCuidador(iv,clave);
        encryptionServicePaciente= new EncryptionServicePaciente(iv,clave);

    }
    public CuidadorPaciente desencriptar(CuidadorPaciente cuidadorPaciente) {
        try {
            if(cuidadorPaciente==null)return null;
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            try {
                if (cuidadorPaciente.getCuidador() != null) {
                    Cuidador cuidador=new Cuidador(cuidadorPaciente.getCuidador());
                    cuidadorPaciente.setCuidador(encryptionServiceCuidador.desencriptar(cuidador));
                }
            }catch (Exception e){
                System.out.println("ya esta desencriptado");
            }
            try {
                if (cuidadorPaciente.getPaciente() != null) {
                    Paciente paciente=new Paciente(cuidadorPaciente.getPaciente());
                    cuidadorPaciente.setPaciente(encryptionServicePaciente.desencriptar(paciente));
                }
            }catch (Exception e){
                System.out.println("ya esta desencriptado");
            }

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return cuidadorPaciente;
    }

    public CuidadorPaciente encriptar(CuidadorPaciente cuidadorPaciente){
        try {
            if(cuidadorPaciente==null)return null;
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            try {
                if (cuidadorPaciente.getCuidador() != null) {
                    Cuidador cuidador=new Cuidador(cuidadorPaciente.getCuidador());
                    cuidadorPaciente.setCuidador(encryptionServiceCuidador.encriptar(cuidador));
                }
            }catch (Exception e){
                System.out.println("ya esta encriptado");
            }
            try {
                if (cuidadorPaciente.getPaciente() != null) {
                    Paciente paciente=new Paciente(cuidadorPaciente.getPaciente());
                    cuidadorPaciente.setPaciente(encryptionServicePaciente.encriptar(paciente));
                }
            }catch (Exception e){
                System.out.println("ya esta encriptado");
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return cuidadorPaciente;
    }

}