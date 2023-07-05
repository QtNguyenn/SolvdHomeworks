package com.project.models;

public class Car {
    private int carID;
    private String licensePlate;
    private String make, model;
    private int year;
    private int driverID;

    public Car(){}
    public Car(int carID,String licensePlate, String make,String model,int year, int driverID)
    {
        this.carID = carID;
        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
        this.year = year;
        this.driverID = driverID;
    }

    //getter
    public int getCarID()
    {
        return carID;
    }
    public String getLicensePlate()
    {
        return licensePlate;
    }

    public String getMake()
    {
        return make;
    }

    public String getModel()
    {
        return model;
    }

    public int getYear ()
    {
        return year;
    }

    public int getDriverID()
    {
        return driverID;
    }

    //setter

    public void setCarID(int carID)
    {
        this.carID = carID;
    }
    public void setLicensePlate(String licensePlate)
    {
        this.licensePlate = licensePlate;
    }

    public void setMake(String make)
    {
     this.make = make;   
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public void setYear (int year)
    {
       this.year= year;
    }

    public void setDriverID(int driverID)
    {
        this.driverID = driverID;
    }

}
