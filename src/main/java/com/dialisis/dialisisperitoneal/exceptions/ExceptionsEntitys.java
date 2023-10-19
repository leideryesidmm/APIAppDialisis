package com.dialisis.dialisisperitoneal.exceptions;
import com.dialisis.dialisisperitoneal.persistence.repository.AlergiaRepository;
import org.springframework.stereotype.Component;

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
        if(!nombre){
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
