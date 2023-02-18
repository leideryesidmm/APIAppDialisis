package com.Dialisis.DialisisPeritoneal.exceptions;

public class PdfException extends RuntimeException{
    private static final String descripcion="Token with wrong format";
    public PdfException(String detail) {
        super(descripcion+" , " + detail);
    }
}
