package com.dialisis.dialisisperitoneal.exceptions;

    public class FieldInvalidException extends BadRequestException{

        private static final String DESCRIPCION="Field with data invalid";
        public FieldInvalidException(String detail) {
            super(DESCRIPCION+" , " + detail);
        }
    }

