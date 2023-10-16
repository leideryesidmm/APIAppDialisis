package com.Dialisis.DialisisPeritoneal.exceptions;

public class UnauthorizedException extends  RuntimeException{
    private static final String DESCRIPCION="Token with wrong format";
    public UnauthorizedException(String detail) {
        super(DESCRIPCION+" , " + detail);
    }
}
