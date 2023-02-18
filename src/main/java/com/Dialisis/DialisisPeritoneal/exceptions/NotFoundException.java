package com.Dialisis.DialisisPeritoneal.exceptions;

public class NotFoundException extends RuntimeException{

    private static final String descripcion="Token with wrong format";
    public NotFoundException(String detail) {
        super(descripcion+" , " + detail);
    }
}
