package com.project.models;

public class RideService {
    private int rideID;
    private int cost,distant;
    private int pickUpTime,dropOffTime;
    private int driverID,passengerID;
    private int pickUpID,dropOffID;

    public RideService(){}

    public RideService(int rideID,int cost,int distant,int pickUpTime, int dropOffTime,
                    int passengerID, int driverID,int pickUpID,int dropOffID)
    {
        this.rideID = rideID;
        this.cost = cost;
        this.distant = distant;
        this.pickUpTime = pickUpTime;
        this.dropOffTime = dropOffTime;
        this.passengerID = passengerID;
        this.driverID = driverID;
        this.pickUpID = pickUpID;
        this.dropOffID = dropOffID;
    }

    public int getRideID()
    {
        return rideID;
    }

    public int getCost()
    {
        return cost;
    }

    public int getDistant()
    {
        return distant;
    }

    public int getPickUpTime()
    {
        return pickUpTime;
    }

    public int getDropOffTime()
    {
        return dropOffTime;
    }

    public int getPassengerID()
    {
        return passengerID;
    }

    public int getDriverID()
    {
        return driverID;
    }

    public int getPickUpID()
    {
        return pickUpID;
    }

    public int getDropOffID()
    {
        return dropOffID;
    }

    //setter
    public void setRideID(int rideID)
    {
        this.rideID = rideID;
    }

    public void setCost(int cost)
    {
        this.cost = cost;
    }

    public void setDistant(int distant)
    {
        this.distant = distant;
    }

    public void setPickUpTime(int pickUpTime)
    {
        this.pickUpTime = pickUpTime;
    }

    public void setDropOffTime(int dropOffTime)
    {
        this.dropOffTime = dropOffTime;
    }

    public void setPassengerID(int passengerID)
    {
        this.passengerID = passengerID;
    }

    public void setDriverID(int driverID)
    {
        this.driverID = driverID;
    }

    public void setPickUpLocationID(int pickUpID)
    {
        this.pickUpID = pickUpID;
    }

    public void setDropOffLocationID( int dropOffID)
    {
        this.dropOffID = dropOffID;
    }
}
