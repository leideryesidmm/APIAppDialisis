package com.Dialisis.DialisisPeritoneal.exceptions;

public class UnauthorizedException extends  RuntimeException{
    private static final String descripcion="Token with wrong format";
    public UnauthorizedException(String detail) {
        super(descripcion+" , " + detail);
    }
}
