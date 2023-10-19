package com.dialisis.dialisisperitoneal.exceptions;

public class ErrorMessage {
    private String exception;
    private String message;
    private String path;

    public ErrorMessage(Exception exception, String path){
        this.exception=exception.getClass().getSimpleName();
        this.message=exception.getMessage();
        this.path=path;
    }

    public String getException(){
        return exception;
    }
    public String getPath() {
        return path;
    }
    public String getMessage(){
        return message;
    }

    public String toString(){
        return "ErrorMesagee{"+
                "Exception='"+exception+'\''+
                "message='" +message+'\''+
                "path='" +path+'\''+
                '}';
    }
}
