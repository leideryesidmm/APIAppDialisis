package com.dialisis.dialisisperitoneal.service.dto.uniones;

import org.springframework.web.multipart.MultipartFile;

public class UnionPacienteFotoInDto {

    private String cedula;
    private MultipartFile foto;

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public MultipartFile getFoto() {
        return foto;
    }

    public void setFoto(MultipartFile foto) {
        this.foto = foto;
    }
}
