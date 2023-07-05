package com.project.models;

public class RideDriver {
    int driverID;
    String name;
    String email;
    int phone;
    int accountID;


    public RideDriver(){}
    
    public RideDriver (int driverID, String name, String email,int phone, int accountID)
    {
        this.driverID = driverID;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.accountID = accountID;
    }

    //getter
    public int getDriverID()
    {
        return driverID;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public int getPhone()
    {
        return phone;
    }

    public int getAccountID()
    {
        return accountID;
    }

    //setter
    public void setdriverID(int driverID)
    {
        this.driverID = driverID;
    }

    public void setName(String name)
    {
        this.name= name;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPhone (int phone)
    {
        this.phone = phone;
    }

    public void setAccountID (int accountID)
    {
        this.accountID = accountID;
    }
}
