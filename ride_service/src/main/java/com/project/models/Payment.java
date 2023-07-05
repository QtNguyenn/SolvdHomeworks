package com.project.models;

public class Payment {
    private int paymentID;
    private double amount, discountAmount;
    private String date;
    private int rideServiceID;

    public Payment(){}

    public Payment(int paymentID, double amount, double discountAmount, String date,int rideServiceID)
    {
        this.paymentID = paymentID;
        this.amount = amount;
        this.discountAmount = discountAmount;
        this.date = date;
        this.rideServiceID = rideServiceID;
    }

    //getter
    public int getPaymentID()
    {
        return paymentID;
    }
    public double getAmount()
    {
        return amount;
    }

    public double getDiscountAmount()
    {
        return discountAmount;
    }

    public String getDate()
    {
        return date;
    }

    public int getRideServiceID()
    {
        return rideServiceID;
    }

    //setter
    public void setPaymentID(int paymentID)
    {
        this.paymentID = paymentID;
    }
    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public void setDiscountAmount(double discountAmount)
    {
        this.discountAmount= discountAmount;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public void setRideService(int rideServiceID)
    {
        this.rideServiceID = rideServiceID;
    }
}
