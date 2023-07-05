package com.project.models;

public class Promotion {
    private int promotionID;
    private String code;

    public Promotion(){}

    public Promotion(int promotionID,String code)
    {
        this.promotionID = promotionID;
        this.code = code;
    }

    public int getPromotionID()
    {
        return promotionID;
    }

    public String getPromotionCode()
    {
        return code;
    }

    public void setPromotionID(int promotionID)
    {
        this.promotionID = promotionID;
    }

    public void setPromotionCode(String code)
    {
        this.code = code;
    }
}
