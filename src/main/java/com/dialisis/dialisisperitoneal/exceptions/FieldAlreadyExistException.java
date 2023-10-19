package com.dialisis.dialisisperitoneal.exceptions;

public class FieldAlreadyExistException extends ConflictException{

    private static final String DESCRIPCION="Field Already Existe";
    public FieldAlreadyExistException(String detail) {
        super(DESCRIPCION+" , " + detail);
    }
}
