package com.Dialisis.DialisisPeritoneal.exceptions;

public class MalFormedHeaderException extends BadRequestException{

    private static final String DESCRIPCION="Token with wrong format";
    public MalFormedHeaderException(String detail) {
        super(DESCRIPCION+" , " + detail);
    }
}
