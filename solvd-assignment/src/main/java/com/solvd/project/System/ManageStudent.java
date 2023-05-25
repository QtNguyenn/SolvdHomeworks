package com.solvd.project.System;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.project.Exceptions.InvalidInputException;
import com.solvd.project.People.Person;
import com.solvd.project.People.Student;

public class ManageStudent<T extends Person> extends CollegeManagement {

    private CustomLinkedList<T> students; // linked list to store students
    private final String FILENAME = "src/main/java/com/solvd/project/Data/studentData.csv"; // filename to read and write student data
    private final Logger logger = LogManager.getLogger(ManageStudent.class); // logger instance

    public ManageStudent() 
    {
        students = new CustomLinkedList<T>();
    }

    // Add a student to the linked list
    public void addStudent(T student) 
    {
        try {
            if (student == null) 
            {
                throw new InvalidInputException("Invalid input: student is null");
            }
            students.add(student);
            //saveToFile(FILENAME, students);
            logger.info("Successfully added student " + student.toString());
        } catch (InvalidInputException e) {
            //System.out.println("Error: " + e.getMessage());
            logger.error("Invalid input", e);
        }
    }

    // Save the linked list to a file
    public void saveToFile() 
    {
        logger.info("Saving student data to file " + FILENAME);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME,true))) {

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
            logger.error("Error writing to file: " + FILENAME, e);
        }
    }


    //lambda
    public List<T> filterStudents(Predicate<T> predicate)
    {
        List<T> studentsFiltered = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) 
        {

            T student = students.get(i);
            if(predicate.test(student))
            {
                studentsFiltered.add(student);
                //System.out.println("Adding" + student.getName());
            }
        }

        return studentsFiltered;
    }

    public void getStudentNames(Consumer<T> processName)
    {
        //List<T> nameList = new ArrayList<>();
        for(int i = 0; i <students.size(); i++)
        {
            T student = students.get(i);
            processName.accept(student);
            //nameList.add(student);
        }
        //return nameList;
    }

    public void processStudents(BiFunction<Student, Integer, String> studentInfoFunction) {
        for (int i = 0; i < students.size(); i++) 
        {
            T student = students.get(i);
            String studentInfo = studentInfoFunction.apply((Student) student, i);
            logger.info(studentInfo);
        }
    }


    public List<T> getStudentsWithFilter(Filter<T> filterStudent) {
        List<T> studentsFiltered = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) 
        {
            T student = students.get(i);
            if (filterStudent.filter(student)) 
            {
                studentsFiltered.add(student);
            }
        }
        return studentsFiltered;
    }

    public List<T> getStudents()
    {
        List<T> studentList = new ArrayList<>();
        CustomLinkedList.Node<T> currentNode = students.getHead();

        while (currentNode != null) {
            studentList.add(currentNode.getData());
            currentNode = currentNode.getNext();
        }

        return studentList;
    }
}
