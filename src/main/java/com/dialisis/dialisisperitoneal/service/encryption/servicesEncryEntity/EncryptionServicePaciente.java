package com.dialisis.dialisisperitoneal.service.encryption.servicesEncryEntity;

import com.dialisis.dialisisperitoneal.persistence.entity.Paciente;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import static org.apache.commons.codec.binary.Base64.encodeBase64;

@Service
public class EncryptionServicePaciente {

    private  String clave;
    private  String iv;

    public EncryptionServicePaciente(String iv, String clave) {
        this.clave = clave;
        this.iv = iv;
    }

    public Paciente desencriptar(Paciente paciente){
        try {
            System.out.println("Recambio del form" + paciente);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            if(paciente.getAltura()!=null) {
                byte[] alturaDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(paciente.getAltura()));
                paciente.setAltura(new String(alturaDesencriptadoBytes, StandardCharsets.UTF_8));
            }
            if(paciente.getDireccion()!=null) {
                byte[] direccionDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(paciente.getDireccion()));
                paciente.setDireccion(new String(direccionDesencriptadoBytes, StandardCharsets.UTF_8));
            }
            if(paciente.getOcupacion()!=null) {
                byte[] ocupacionDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(paciente.getOcupacion()));
                paciente.setOcupacion(new String(ocupacionDesencriptadoBytes, StandardCharsets.UTF_8));
            }
            if(paciente.getPeso()!=null) {
                byte[] pesoDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(paciente.getPeso()));
                paciente.setPeso(new String(pesoDesencriptadoBytes, StandardCharsets.UTF_8));
            }
            if(paciente.getPesoSeco()!=null) {
                byte[] pesoSecoDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(paciente.getPesoSeco()));
                paciente.setPesoSeco(new String(pesoSecoDesencriptadoBytes, StandardCharsets.UTF_8));
            }
            if(paciente.getCedula()!=null) {
                byte[] cedulaDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(paciente.getCedula()));
                paciente.setCedula(new String(cedulaDesencriptadoBytes, StandardCharsets.UTF_8));
            }
            if(paciente.getRh()!=null) {
                byte[] rhDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(paciente.getRh()));
                paciente.setRh(new String(rhDesencriptadoBytes, StandardCharsets.UTF_8));
            }
            if(paciente.getTipoSangre()!=null) {
                byte[] tipoSangreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(paciente.getTipoSangre()));
                paciente.setTipoSangre(new String(tipoSangreDesencriptadoBytes, StandardCharsets.UTF_8));
            }
            if(paciente.getContrasenia()!=null){
                byte[] contraseniaDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(paciente.getContrasenia()));
                paciente.setContrasenia(new String (contraseniaDesencriptadoBytes, StandardCharsets.UTF_8));}
            if(paciente.getCelular()!=null){
                byte[] celularDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(paciente.getCelular()));
                paciente.setCelular(new String (celularDesencriptadoBytes, StandardCharsets.UTF_8));}
            if(paciente.getCorreo()!=null){
                byte[] correoDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(paciente.getCorreo()));
                paciente.setCorreo(new String (correoDesencriptadoBytes, StandardCharsets.UTF_8));}
            if(paciente.getTipoDocumento()!=null){
                byte[] tipoDocumentoDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(paciente.getTipoDocumento()));
                paciente.setTipoDocumento(new String (tipoDocumentoDesencriptadoBytes,StandardCharsets.UTF_8));}
            if(paciente.getNombre()!=null){
                byte[] nombreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(paciente.getNombre()));
                paciente.setNombre(new String (nombreDesencriptadoBytes, StandardCharsets.UTF_8));}
            System.out.println("nuevo usuario "+paciente);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return paciente;
    }
    public Paciente encriptar(Paciente paciente){
        try {
            System.out.println("usuario del form" + paciente);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            // Inicializa el cifrado en modo cifrado
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            if(paciente.getAltura()!=null) {
                byte[] alturaDesencriptadoBytes = cipher.doFinal(paciente.getAltura().getBytes());
                paciente.setAltura(new String(encodeBase64(alturaDesencriptadoBytes)));
            }
            if(paciente.getDireccion()!=null) {
                byte[] direccionDesencriptadoBytes = cipher.doFinal(paciente.getDireccion().getBytes());
                paciente.setDireccion(new String(encodeBase64(direccionDesencriptadoBytes)));
            }
            if(paciente.getOcupacion()!=null) {
                byte[] ocupacionDesencriptadoBytes = cipher.doFinal(paciente.getOcupacion().getBytes());
                paciente.setOcupacion(new String(encodeBase64(ocupacionDesencriptadoBytes)));
            }
            if(paciente.getPeso()!=null) {
                byte[] pesoDesencriptadoBytes = cipher.doFinal(paciente.getPeso().getBytes());
                paciente.setPeso(new String(encodeBase64(pesoDesencriptadoBytes)));
            }
            if(paciente.getPesoSeco()!=null) {
                byte[] pesoSecoDesencriptadoBytes = cipher.doFinal(paciente.getPesoSeco().getBytes());
                paciente.setPesoSeco(new String(encodeBase64(pesoSecoDesencriptadoBytes)));
            }
            if(paciente.getRh()!=null) {
                byte[] rhDesencriptadoBytes = cipher.doFinal(paciente.getRh().getBytes());
                paciente.setRh(new String(encodeBase64(rhDesencriptadoBytes)));
            }
            if(paciente.getTipoSangre()!=null) {
                byte[] tipoSangreDesencriptadoBytes = cipher.doFinal(paciente.getTipoSangre().getBytes());
                paciente.setTipoSangre(new String(encodeBase64(tipoSangreDesencriptadoBytes)));
            }
            if(paciente.getCedula()!=null) {
                byte[] cedulaDesencriptadoBytes = cipher.doFinal(paciente.getCedula().getBytes());
                paciente.setCedula(new String(encodeBase64(cedulaDesencriptadoBytes)));
            }
            if(paciente.getContrasenia()!=null){
                byte[] contraseniaEncriptada = cipher.doFinal(paciente.getContrasenia().getBytes());
                paciente.setContrasenia(new String(encodeBase64(contraseniaEncriptada)));}
            if(paciente.getCelular()!=null){
                byte[] celularEncriptado = cipher.doFinal(paciente.getCelular().getBytes());
                paciente.setCelular(new String(encodeBase64(celularEncriptado)));}
            if(paciente.getNombre()!=null){
                byte[] nombreEncriptado = cipher.doFinal(paciente.getNombre().getBytes());
                paciente.setNombre(new String(encodeBase64(nombreEncriptado)));}
            if(paciente.getCorreo()!=null){
                byte[] correoEncriptado = cipher.doFinal(paciente.getCorreo().getBytes());
                paciente.setCorreo(new String(encodeBase64(correoEncriptado)));}
            if(paciente.getTipoDocumento()!=null){
                byte[] tipoDocumentoEncriptado = cipher.doFinal(paciente.getTipoDocumento().getBytes());
                paciente.setTipoDocumento(new String(encodeBase64(tipoDocumentoEncriptado)));}
            System.out.println("nuevo usuario "+paciente);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return paciente;
    }
}