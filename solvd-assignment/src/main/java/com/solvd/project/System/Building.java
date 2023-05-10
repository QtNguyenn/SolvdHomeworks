package com.solvd.project.System;

public class Building {
    private String buildingName;
    private int numberOfRoom;

    public Building()
    {
        buildingName = "";
        numberOfRoom = 0;
    }
    public Building( String buildingName, int numberOfRoom)
    {
        this.buildingName = buildingName;
        this.numberOfRoom = numberOfRoom;
    }

    public String getBuildingName()
    {
        return buildingName;
    }
    public int getNumberOfRoom()
    {
        return numberOfRoom;
    }
    public void setBuildingName(String buildingName)
    {
        this.buildingName = buildingName;
    }
    public void setNumberOfRoom(int numberOfRoom)
    {
        this.numberOfRoom = numberOfRoom;
    }
}
