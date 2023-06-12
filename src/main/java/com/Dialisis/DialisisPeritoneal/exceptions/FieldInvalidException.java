package com.Dialisis.DialisisPeritoneal.exceptions;

    public class FieldInvalidException extends BadRequestException{

        private static final String descripcion="Field with data invalid";
        public FieldInvalidException(String detail) {
            super(descripcion+" , " + detail);
        }
    }

