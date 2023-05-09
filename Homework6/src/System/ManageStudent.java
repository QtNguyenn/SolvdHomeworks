package src.System;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import src.Exceptions.InvalidInputException;
import src.People.Person;
import src.People.Student;

public class ManageStudent<T extends Person> extends CollegeManagement {

    private CustomLinkedList<T> students; // linked list to store students
    private final String FILENAME = "Homework6/studentData.csv"; // filename to read and write student data
    private final Logger logger = LogManager.getLogger(ManageStudent.class); // logger instance

    public ManageStudent() 
    {
        students = new CustomLinkedList<T>();
    }

    // Add a student to the linked list
    public void add(T student) 
    {
        try {
            if (student == null) 
            {
                throw new InvalidInputException("Invalid input: student is null");
            }
            students.add(student);
            saveToFile(FILENAME, students);
            logger.info("Successfully added student " + student.toString());
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
            logger.error("Invalid input", e);
        }
    }

    // Save the linked list to a file
    private void saveToFile(String fileName, CustomLinkedList<T> students) 
    {
        logger.info("Saving student data to file " + fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true))) {
            for (int i = 0; i < students.size(); i++) 
            {
                T student = students.get(i);
                writer.write(String.format("%s,%s,%d,%s,%s,%s,%s,%s,%s\n", 
                student.getName(), 
                student.getStreetAddress(), 
                student.getAge(),
                student.getId(),
                student.getPhoneNumber(),
                ((Student) student).getSchoolName(),
                ((Student) student).getSchoolAddress(),
                ((Student) student).getAcademicStanding(),
                ((Student) student).getMajor()));

            }
        } catch (IOException e) {
            logger.error("Error writing to file: " + fileName, e);
        }
    }
}
