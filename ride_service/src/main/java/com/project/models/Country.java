package com.project.models;

public class Country {
    private int countryID;
    private String name;
    private int cityID;


    public Country(){}
    
    public Country(int countryID, String name, int cityID)
    {
       this.countryID = countryID;
       this.name = name;
       this.cityID = cityID;
    }

    //getter
    public int getCountryID()
    {
        return countryID;
    }

    public String getCountryName()
    {
        return name;
    }

    public int getCityID()
    {
        return cityID;
    }

    //setter
    public void setCountryID(int countryID)
    {
        this.countryID = countryID;
    }

    public void setCountryName(String name)
    {
        this.name = name;
    }

    public void setCityID(int cityID)
    {
        this.cityID = cityID;
    }
}
