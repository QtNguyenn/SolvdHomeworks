package com.project.Exception;

public class NoAccountExist extends Exception{
    public NoAccountExist()
    {
        super("ID Doesn't Exist.");
    }
}
