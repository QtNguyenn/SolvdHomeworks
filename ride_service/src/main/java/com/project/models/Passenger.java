package com.project.models;

public class Passenger {
    private int passengerID;
    private String name;
    private String email;
    private int phone;
    private int accountID;
    
    public Passenger(){}
    public Passenger (int passengerID, String name, String email,int phone, int accountID)
    {
        this.passengerID = passengerID;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.accountID = accountID;
    }

    //getter
    public int getPassengerID()
    {
        return passengerID;
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
    public void setPassengerID(int passengerID)
    {
        this.passengerID = passengerID;
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
