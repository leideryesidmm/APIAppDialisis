package com.Dialisis.DialisisPeritoneal.exceptions;

public class BadRequestException extends RuntimeException{
    private static final String DESCRIPCION="Bad Request Exception (400)";
    public BadRequestException(String detail) {
        super(DESCRIPCION+" , " + detail);}
}
