package com.solvd.project.System;

import com.solvd.project.People.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CollegeManagement
{
    private static final Logger logger = LogManager.getLogger(CollegeManagement.class);

    public static void manageStudent(Student newStudent)
    {    
        //Scanner scanner = new Scanner(System.in); // create scanner object to read user input
        //CustomLinkedList<Student> studentList = new CustomLinkedList<>();
        ManageStudent<Student> studentList = new ManageStudent<>();    
        logger.info("Managing Student's Info.");
        studentList.add(newStudent);
    }

    public static void manageProfessor(Professor newProfessor)
    {    
        //Scanner scanner = new Scanner(System.in); // create scanner object to read user input
        logger.info("Managing Professor's Info.");
        ManageProfessor.add(newProfessor);         
    }
}
