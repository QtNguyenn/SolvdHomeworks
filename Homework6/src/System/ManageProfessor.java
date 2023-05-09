package src.System;
import java.util.ArrayList; //import ArrayList class
import java.util.HashMap;   //import Hashmap
import java.util.Queue;  //import Queue

import java.util.HashSet; //import harshSet
import java.util.LinkedList;

import src.Exceptions.InvalidInputException;
import src.People.*;

import java.io.BufferedReader;
import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ManageProfessor extends CollegeManagement{
    // Declare a private static field called professorList that is an ArrayList of Professor objects
    private static ArrayList<Professor> professorList = new ArrayList<>();
    private static HashSet<Integer> writtenIds = new HashSet<>(); // create a set to keep track of written IDs
    // Declare a private static field professorMap Hashmap
    private static HashMap<Integer, Professor> professorMap = new HashMap<>();
    // Declare a queue of professors in the order they were added
    private static Queue<Professor> professorQueue = new LinkedList<>();
    //Declare a filename to read and write to
    private static final String FILENAME = "Homework6/professorData.csv"; // file name to read and write professor data

    //Define a logger instance
    private static final Logger logger = LogManager.getLogger(ManageProfessor.class);
    public static void add(Professor newProfessor)
    {
        addProfessor(newProfessor);
    }
    //Define a method called addProfessor() that add new professor to the professorList 
    private static void addProfessor(Professor newProfessor)
    {   try{
            if(newProfessor.equals(null))
            {
                throw new InvalidInputException("Invalid");
            }
            professorList.add(newProfessor);
            logger.info("Sucessfully added professor.");
            saveToFile(FILENAME);
        } catch (InvalidInputException er)
        {
            System.out.println("Error: " + er.getMessage());
            logger.error("Error input",er);
        }

    }

    private static void saveToFile(String fileName)
    {   
        logger.info("Adding Professor's Info to Database");
        try
        {
            //create new file object with specified file name
            File file = new File(fileName);
            //check if the file already exist
            if(!file.exists())
            {
                logger.info("Creating new data file...");
                file.createNewFile();
            }
            
            FileWriter writer = new FileWriter(fileName,true);
            //uses arraylist collection to store professor's info  and uses hashSet collection to set written ID 
            //so we don't dupplicate professor's info when write to file
            for (Professor professor : professorList) 
            {
                if (!writtenIds.contains(professor.getId())) 
                { // check if the ID has already been written
                       
                    writer.write(String.format("%s,%s,%d,%s,%s,%s,%s,%s,%s\n", 
                    professor.getName(), 
                    professor.getStreetAddress(), 
                    professor.getAge(),
                    professor.getId(),
                    professor.getPhoneNumber(),
                    professor.getSchoolName(),
                    professor.getSchoolAddress(),
                    professor.getDepartment(),
                    professor.getCourse()));

                    writtenIds.add(professor.getId()); // add the ID to the set of written IDs
                }
            }
            writer.close();
            logger.info("Professor Successfully added.");
        }
        catch (IOException e) 
        {
            // Handle any IOExceptions that may occur while reading from the file
            System.out.println("An error occurred while writing to file.");
            return;
        } 
    }

    public static HashMap<Integer,Professor> searchData()
    {
        try(BufferedReader br = new BufferedReader(new FileReader(FILENAME)))
        {
            String line;
            while((line = br.readLine()) != null)
            {
                String [] parts = line.split(",");
                String name = parts [0];
                String address = parts[1];
                int age = Integer.parseInt(parts[2]);
                int id = Integer.parseInt(parts[3]);
                int phone = Integer.parseInt(parts[4]);
                String schoolName = parts[5];
                String schoolAddress = parts[6];
                String department = parts[7];
                String course = parts[8];
                professorList.add(new Professor(name,address,age,id,phone,schoolName,schoolAddress,department,course));
                // Close the file
                
            }
            br.close();
        } catch (IOException e)
        {
            logger.error("Cannot Read file");
        }

        
        for (Professor professor : professorList) {
            professorMap.put(professor.getId(), professor);
        }

        return professorMap;
    }

    public static Queue<Professor> testQueue()
    {
        

        try(BufferedReader br1 = new BufferedReader(new FileReader(FILENAME)))
        {

            String line;
            while((line = br1.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                String address = parts[1];
                int age = Integer.parseInt(parts[2]);
                int id = Integer.parseInt(parts[3]);
                int phone = Integer.parseInt(parts[4]);
                String schoolName = parts[5];
                String schoolAddress = parts[6];
                String department = parts[7];
                String course = parts[8];   
                professorQueue.add(new Professor(name, address, age, id, phone, schoolName, schoolAddress, department, course));  
            }
            br1.close();
        } catch (IOException e) {
            logger.error("Cannot read file");
        }
        return professorQueue;
            
    }
}
