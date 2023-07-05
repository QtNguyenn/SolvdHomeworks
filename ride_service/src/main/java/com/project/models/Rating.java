package com.project.models;

public class Rating {
    private int ratingID;
    private String comment;
    private int rating;
    private int driverID, passengerID;

    public Rating(){}
    
    public Rating(int ratingID,String comment, int rating, int driverID, int passengerID)
    {
        this.ratingID = ratingID;
        this.comment= comment;
        this.rating = rating;
        this.driverID = driverID;
        this.passengerID = passengerID;
    }

    //getter
    public int getRatingID()
    {
        return ratingID;
    }

    public String getComment()
    {
        return comment;
    }

    public int getRating()
    {
        return rating;
    }

    public int getPassengerID()
    {
        return passengerID;
    }

    public int getDriverID()
    {
        return driverID;
    }

    //setter
    public void setRatingID(int ratingID)
    {
        this.ratingID = ratingID;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public void setRating (int rating)
    {
        this.rating = rating;
    }
    public void setPassengerID(int passengerID)
    {
        this.passengerID = passengerID;
    }

    public void setDriverID(int driverID)
    {
        this.driverID = driverID;
    }
}
