package com.Dialisis.DialisisPeritoneal.exceptions;

public class FieldAlreadyExistException extends ConflictException{

    private static final String descripcion="Field Already Existe";
    public FieldAlreadyExistException(String detail) {
        super(descripcion+" , " + detail);
    }
}
