package com.solvd.project.System;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public enum Subject {
    
      
    COMPUTER_SCIENCE("Computer Science",150),
    BUSINESS("Business Administration", 101),
    PHYSICS("Physics", 100),
    ENGINEERING("Engineering", 220),
    MATH("Mathematic", 101);

    private String description;
    private int id;
    private static final Logger logger = LogManager.getLogger(Subject.class); 
    
    Subject(String description, int id)
    {
        this.description = description;
        this.id = id;
    }

    public String getDescription()
    {
        return description;
    }

    public int getId()
    {
        return id;
    }

    public String printDetail()
    {
        return "Major: " +description 
                +"\nId: " + id;
    }

    static {
        logger.info("Major's details ...");
    }
}
