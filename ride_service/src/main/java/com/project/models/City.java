package com.project.models;

public class City {
    private int cityID;
    private String name;
    private int locationID;

    public City(){}

    public City(int cityID, String name, int locationID)
    {
        this.cityID = cityID;
        this.name = name;
        this.locationID = locationID;
    }

    //getter
    public int getCityID()
    {
        return cityID;
    }

    public String getCityName()
    {
        return name;
    }

    public int getLocationID()
    {
        return locationID;
    }

    //setter
    public void setCityID(int cityID)
    {
        this.cityID = cityID;
    }

    public void setCityName(String name)
    {
        this.name = name;
    }

    public void setLocation(int locationID)
    {
        this.locationID = locationID;
    }
}
