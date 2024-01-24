package com.dialisis.dialisisperitoneal.service.encryption.servicesEncryEntity;

import com.dialisis.dialisisperitoneal.persistence.entity.ChequeoMensual;
import com.dialisis.dialisisperitoneal.persistence.entity.Cita;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.apache.commons.codec.binary.Base64.encodeBase64;

public class EncryptionServiceChequeo {
    private String iv ;
    private String clave;
    EncryptionServiceCita encryptionServiceCita;

    public EncryptionServiceChequeo(String iv, String clave) {
        this.iv = iv;
        this.clave = clave;
        this.encryptionServiceCita = new EncryptionServiceCita(iv,clave);
    }
    public ChequeoMensual desencriptar(ChequeoMensual chequeo) {
        try {
            if(chequeo==null)return null;
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            if(chequeo.getColesterolTotal()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(chequeo.getColesterolTotal()));
                chequeo.setColesterolTotal(new String(nombreDesencriptadoBytes));
            }
            if(chequeo.getFosforo()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(chequeo.getFosforo()));
                chequeo.setFosforo(new String(nombreDesencriptadoBytes));
            }
            if(chequeo.getGlicemia()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(chequeo.getGlicemia()));
                chequeo.setGlicemia(new String(nombreDesencriptadoBytes));
            }
            if(chequeo.getHdl()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(chequeo.getHdl()));
                chequeo.setHdl(new String(nombreDesencriptadoBytes));
            }
            if(chequeo.getHemoglobina()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(chequeo.getHemoglobina()));
                chequeo.setHemoglobina(new String(nombreDesencriptadoBytes));
            }
            if(chequeo.getLdh()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(chequeo.getLdh()));
                chequeo.setLdh(new String(nombreDesencriptadoBytes));
            }
            if(chequeo.getNitrogenoUreico()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(chequeo.getNitrogenoUreico()));
                chequeo.setNitrogenoUreico(new String(nombreDesencriptadoBytes));
            }
            if(chequeo.getPotasio()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(chequeo.getPotasio()));
                chequeo.setPotasio(new String(nombreDesencriptadoBytes));
            }
            if(chequeo.getTensionArterial()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(chequeo.getTensionArterial()));
                chequeo.setTensionArterial(new String(nombreDesencriptadoBytes));
            }
            if(chequeo.getTrigliceridos()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(chequeo.getTrigliceridos()));
                chequeo.setTrigliceridos(new String(nombreDesencriptadoBytes));
            }
            if(chequeo.getPeso()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(chequeo.getPeso()));
                chequeo.setPeso(new String(nombreDesencriptadoBytes));
            }
            if(chequeo.getPesoSeco()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(chequeo.getPesoSeco()));
                chequeo.setPesoSeco(new String(nombreDesencriptadoBytes));
            }
            if(chequeo.getGlucosa()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(chequeo.getGlucosa()));
                chequeo.setGlucosa(new String(nombreDesencriptadoBytes));
            }
            if(chequeo.getCreatinina()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(chequeo.getCreatinina()));
                chequeo.setCreatinina(new String(nombreDesencriptadoBytes));
            }
            if(chequeo.getKtv()!=null) {
                byte[] nombreDesencriptadoBytes = cipher.doFinal(Base64.getDecoder().decode(chequeo.getKtv()));
                chequeo.setKtv(new String(nombreDesencriptadoBytes));
            }
            if(chequeo.getCita()!=null) {
                Cita cita=new Cita(chequeo.getCita());
                chequeo.setCita(encryptionServiceCita.desencriptar(cita));
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return chequeo;
    }

    public ChequeoMensual encriptar(ChequeoMensual chequeo){
        try {
            if(chequeo==null)return null;
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            if(chequeo.getColesterolTotal()!=null) {
                byte[] colDesencriptadoBytes = cipher.doFinal(chequeo.getColesterolTotal().getBytes());
                chequeo.setColesterolTotal(new String(encodeBase64(colDesencriptadoBytes)));
            }
            if(chequeo.getFosforo()!=null) {
                byte[] fosDesencriptadoBytes = cipher.doFinal(chequeo.getFosforo().getBytes());
                chequeo.setFosforo(new String(encodeBase64(fosDesencriptadoBytes)));
            }
            if(chequeo.getGlicemia()!=null) {
                byte[] gliDesencriptadoBytes = cipher.doFinal(chequeo.getGlicemia().getBytes());
                chequeo.setGlicemia(new String(encodeBase64(gliDesencriptadoBytes)));
            }
            if(chequeo.getHdl()!=null) {
                byte[] hdlDesencriptadoBytes = cipher.doFinal(chequeo.getHdl().getBytes());
                chequeo.setHdl(new String(encodeBase64(hdlDesencriptadoBytes)));
            }
            if(chequeo.getHemoglobina()!=null) {
                byte[] hemoDesencriptadoBytes = cipher.doFinal(chequeo.getHemoglobina().getBytes());
                chequeo.setHemoglobina(new String(encodeBase64(hemoDesencriptadoBytes)));
            }
            if(chequeo.getLdh()!=null) {
                byte[] ldhDesencriptadoBytes = cipher.doFinal(chequeo.getLdh().getBytes());
                chequeo.setLdh(new String(encodeBase64(ldhDesencriptadoBytes)));
            }
            if(chequeo.getNitrogenoUreico()!=null) {
                byte[] nitroDesencriptadoBytes = cipher.doFinal(chequeo.getNitrogenoUreico().getBytes());
                chequeo.setNitrogenoUreico(new String(encodeBase64(nitroDesencriptadoBytes)));
            }
            if(chequeo.getPotasio()!=null) {
                byte[] potDesencriptadoBytes = cipher.doFinal(chequeo.getPotasio().getBytes());
                chequeo.setPotasio(new String(encodeBase64(potDesencriptadoBytes)));
            }
            if(chequeo.getTensionArterial()!=null) {
                byte[] tensDesencriptadoBytes = cipher.doFinal(chequeo.getTensionArterial().getBytes());
                chequeo.setTensionArterial(new String(encodeBase64(tensDesencriptadoBytes)));
            }
            if(chequeo.getTrigliceridos()!=null) {
                byte[] triDesencriptadoBytes = cipher.doFinal(chequeo.getTrigliceridos().getBytes());
                chequeo.setTrigliceridos(new String(encodeBase64(triDesencriptadoBytes)));
            }
            if(chequeo.getPeso()!=null) {
                byte[] pesDesencriptadoBytes = cipher.doFinal(chequeo.getPeso().getBytes());
                chequeo.setPeso(new String(encodeBase64(pesDesencriptadoBytes)));
            }
            if(chequeo.getPesoSeco()!=null) {
                byte[] secDesencriptadoBytes = cipher.doFinal(chequeo.getPesoSeco().getBytes());
                chequeo.setPesoSeco(new String(encodeBase64(secDesencriptadoBytes)));
            }
            if(chequeo.getGlucosa()!=null) {
                byte[] gluDesencriptadoBytes = cipher.doFinal(chequeo.getGlucosa().getBytes());
                chequeo.setGlucosa(new String(encodeBase64(gluDesencriptadoBytes)));
            }
            if(chequeo.getCreatinina()!=null) {
                byte[] creDesencriptadoBytes = cipher.doFinal(chequeo.getCreatinina().getBytes());
                chequeo.setCreatinina(new String(encodeBase64(creDesencriptadoBytes)));
            }
            if(chequeo.getKtv()!=null) {
                byte[] ktvDesencriptadoBytes = cipher.doFinal(chequeo.getKtv().getBytes());
                chequeo.setKtv(new String(encodeBase64(ktvDesencriptadoBytes)));
            }
            if(chequeo.getCita()!=null) {
                Cita cita=new Cita(chequeo.getCita());
                chequeo.setCita(encryptionServiceCita.encriptar(cita));
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return chequeo;
    }
}
