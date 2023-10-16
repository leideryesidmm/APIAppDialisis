package com.Dialisis.DialisisPeritoneal.exceptions;

public class JwtException extends RuntimeException{

    private static final String DESCRIPCION="Field with data invalid";
    public JwtException(String detail) {
        super(DESCRIPCION+" , " + detail);
    }

}
