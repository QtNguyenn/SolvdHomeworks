package com.project.Exception;

public class DataExistException extends Exception{

    private String customMessage;
    public DataExistException(String message)
    {
        super(message);
        this.customMessage = message;
    }

    public String getMessage() {
        return customMessage;
    }
}
