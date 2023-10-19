package com.dialisis.dialisisperitoneal.exceptions;

public class ForbiddenException extends RuntimeException{

    private static final String DESCRIPCION="Access denied by";
    public ForbiddenException(String detail) {
        super(DESCRIPCION+" : " + detail+ "(Error 403)");
    }
}
