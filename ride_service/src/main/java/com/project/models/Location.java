package com.project.models;

public class Location {
    private int locationID;
    private String nameAddress;
    
    public Location(){}
    public Location(int locationID, String nameAddress)
    {
        this.locationID = locationID;
        this.nameAddress = nameAddress;
    }


    //getter
    public int getLocationID()
    {
        return locationID;
    }

    public String getNameAddress()
    {
        return nameAddress;
    }

    //setter
    public void setLocationID(int locationID)
    {
        this.locationID = locationID;
    }

    public void setNameAddress(String nameAddress)
    {
        this.nameAddress = nameAddress;
    }
}
