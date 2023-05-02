package src.System;
import java.util.ArrayList; //import ArrayList class
import java.util.Scanner;  //Import Scanner class to read user input

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


public class ManageStudent extends CollegeManagement{
    // Declare a private static field called studentList that is an ArrayList of Student objects
    private static ArrayList<Student> studentList = new ArrayList<>();

    //Declare a filename to read and write to
    private static final String FILENAME = "Homework5/studentData.txt"; // file name to read and write student data

    //Define a logger instance
    private static final Logger logger = LogManager.getLogger(ManageStudent.class);
    //Define a method called printMenu() that displays the options available to the user
    protected static void printStudentMenu()
    {
        Scanner scanner = new Scanner(System.in); // create scanner object to read user input

        int option = 0;
        while(option != 5)
        {
            
            System.out.println("\nStudent Managment System");
            System.out.println("\n1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Remove Student");
            System.out.println("4. View Student List");
            System.out.println("5. Exit");
            System.out.println("Enter your choice: ");
            option = scanner.nextInt();
            switch (option)
            {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    //updateStudent(scanner);
                    break;       
                case 3:
                    //removeStudent(FILENAME,scanner);
                    break;
                case 4:
                    //viewStudentList(FILENAME);
                    break;
                case 5:
                    System.out.println("Exiting program..");
                    break;
                default:
                    System.out.println("Invalid option. Please try again");
                    break;     
            } 
        }
        scanner.close();
    }
    //Define a method called addStudent() that add new student to the studentList 
    private static void addStudent(Scanner scanner)
    {   try{
        scanner.nextLine();
        System.out.print("\nEnter name: ");
        String name = scanner.nextLine();
        System.out.print("\nEnter address: ");
        String address = scanner.nextLine();
        System.out.print("\nAge: ");
        int age = scanner.nextInt();
        System.out.print("\nId: ");
        int id = scanner.nextInt();
        System.out.print("\nEnter phone number: ");
        int phone = scanner.nextInt();
        scanner.nextLine();
        System.out.print("\nEnter school name: ");
        String schoolName = scanner.nextLine();
        System.out.print("\nEnter school address: ");
        String schoolAddress = scanner.nextLine();
        System.out.print("\nEnter academic standing: ");
        String academicaStanding = scanner.nextLine();
        System.out.print("\nEnter school major: ");
        String major = scanner.nextLine();
        //Create a new student object with name,id,major
        
        studentList.add(new Student(name,address,age,id,phone,schoolName,schoolAddress,academicaStanding,major));
        System.out.println("Sucessfully added student.");
        saveToFile(FILENAME);
        } catch (InvalidInputException er)
        {
            System.out.println("Error: " + er.getMessage());
            logger.error("Error in major",er);
        }

    }

    private static void saveToFile(String fileName)
    {
        try
        {
            //create new file object with specified file name
            File file = new File(fileName);
            //check if the file already exist
            if(!file.exists())
            {
                System.out.println("Creating new data file...");
                file.createNewFile();
            }
            
            FileWriter writer = new FileWriter(fileName,true);
            for( Student student : studentList)
            {
                writer.write("Name: " + student.getName()+ ", Address: " + student.getStreetAddress() + ", Age: " + student.getAge() 
                + ", ID: "+ student.getId() + ", PhoneNumber: " + student.getPhoneNumber() + ", SchoolName: "+ student.getSchoolName() 
                + ", SchoolAddress: "+ student.getSchoolAddress() + ", Standing: " + student.getAcademicStanding() + ", Major: "+student.getMajor() + "\n");
            }
            writer.close();
        }
        catch (IOException e) 
        {
            // Handle any IOExceptions that may occur while reading from the file
            System.out.println("An error occurred while writing to file.");
            return;
        } 
    }
}
