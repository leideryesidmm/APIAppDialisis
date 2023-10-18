package com.dialisis.dialisisperitoneal.exceptions;

public class MailException extends RuntimeException{

    private static final String DESCRIPCION="Field with data invalid";
    public MailException(String detail) {
        super(DESCRIPCION+" , " + detail);
    }
}
