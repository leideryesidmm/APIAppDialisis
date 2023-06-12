package com.Dialisis.DialisisPeritoneal.exceptions;

public class JwtException extends RuntimeException{

    private static final String descripcion="Field with data invalid";
    public JwtException(String detail) {
        super(descripcion+" , " + detail);
    }

}
