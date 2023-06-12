package com.Dialisis.DialisisPeritoneal.exceptions;

public class MailException extends RuntimeException{

    private static final String descripcion="Field with data invalid";
    public MailException(String detail) {
        super(descripcion+" , " + detail);
    }
}
