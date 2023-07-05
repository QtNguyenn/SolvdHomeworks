package com.project.models;

public class PaymentHasPromotion {
    int paymentID;
    int promotionID;

    public PaymentHasPromotion(){}

    public PaymentHasPromotion(int paymentID,int promotionID)
    {
        this.paymentID = paymentID;
        this.promotionID = promotionID;
    }

    public int getPaymentID()
    {
        return paymentID;
    }

    public int getPromotionID()
    {
        return promotionID;
    }

    public void setPromotionID(int promotionID)
    {
        this.promotionID = promotionID;
    }

    public void setPaymentID(int paymentID)
    {
        this.paymentID = paymentID;
    }
}
