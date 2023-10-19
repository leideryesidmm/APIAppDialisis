package com.dialisis.dialisisperitoneal.exceptions;

public class PdfException extends RuntimeException{
    private static final String DESCRIPCION="Token with wrong format";
    public PdfException(String detail) {
        super(DESCRIPCION+" , " + detail);
    }
}
