package com.Dialisis.DialisisPeritoneal.exceptions;

public class ConflictException extends BadRequestException{

    private static final String descripcion="Conflict in the data (409)";
    public ConflictException(String detail) {
        super(descripcion+" , " + detail);
    }
}
