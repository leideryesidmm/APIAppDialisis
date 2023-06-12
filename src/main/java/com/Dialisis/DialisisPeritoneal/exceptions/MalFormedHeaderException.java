package com.Dialisis.DialisisPeritoneal.exceptions;

public class MalFormedHeaderException extends BadRequestException{

    private static final String descripcion="Token with wrong format";
    public MalFormedHeaderException(String detail) {
        super(descripcion+" , " + detail);
    }
}
