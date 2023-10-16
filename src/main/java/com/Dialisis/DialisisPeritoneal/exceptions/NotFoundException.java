package com.Dialisis.DialisisPeritoneal.exceptions;

public class NotFoundException extends RuntimeException{

    private static final String DESCRIPCION="Token with wrong format";
    public NotFoundException(String detail) {
        super(DESCRIPCION+" , " + detail);
    }
}
