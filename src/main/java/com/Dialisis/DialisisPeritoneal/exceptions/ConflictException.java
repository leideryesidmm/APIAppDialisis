package com.Dialisis.DialisisPeritoneal.exceptions;

public class ConflictException extends RuntimeException{

    private static final String DESCRIPCION="Conflict in the data (409)";
    public ConflictException(String detail) {
        super(DESCRIPCION+" , " + detail);
    }
}
