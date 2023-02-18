package com.Dialisis.DialisisPeritoneal.exceptions;

public class ForbiddenException extends RuntimeException{

    private static final String descripcion="Access denied by";
    public ForbiddenException(String detail) {
        super(descripcion+" : " + detail+ "(Error 403)");
    }
}
