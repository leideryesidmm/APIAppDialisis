package com.Dialisis.DialisisPeritoneal.exceptions;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Alergia;
import com.Dialisis.DialisisPeritoneal.persistence.repository.AlergiaRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.AlergiaInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ExceptionsEntitys {

    private final AlergiaRepository repository;

    public ExceptionsEntitys(AlergiaRepository repository) {
        this.repository = repository;
    }

    public void errorId(int id){
        if(!repository.existsById(id)){
            throw new NotFoundException("id "+ id);
        }
    }

    public void errorString(String cadena){
        boolean nombre=contieneSoloLetras(cadena);
        if(nombre==false){
            throw new FieldInvalidException("nombre " + cadena+ ", no puede contener numeros");
        }
    }
    public void actualizarAlergia(int id, String alergia){
        errorId(id);
        errorString(alergia);
    }

    public static boolean contieneSoloLetras(String cadena) {
        for (int x = 0; x < cadena.length(); x++) {
            char c = cadena.charAt(x);
            // Si no está entre a y z, ni entre A y Z, ni es un espacio
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
                return false;
            }
        }
        return true;
    }

}
