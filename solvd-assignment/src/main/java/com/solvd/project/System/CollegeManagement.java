package com.solvd.project.System;

import com.solvd.project.People.*;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CollegeManagement
{
    private static final Logger logger = LogManager.getLogger(CollegeManagement.class);
    public static ManageStudent<Student> manageStudentList = new ManageStudent<>();
    
    public static void manageStudent(Student newStudent,String save)
    {    
        //Scanner scanner = new Scanner(System.in); // create scanner object to read user input
        //CustomLinkedList<Student> studentList = new CustomLinkedList<>();
        //ManageStudent<Student> manageStudentList = new ManageStudent<>();    
        logger.info("Managing Student's Info.");
        manageStudentList.addStudent(newStudent);
        if(save == "Y")
        {
            manageStudentList.saveToFile();
        }
    }

    public static void manageProfessor(Professor newProfessor)
    {    
        //Scanner scanner = new Scanner(System.in); // create scanner object to read user input
        logger.info("Managing Professor's Info.");
        ManageProfessor.add(newProfessor);         
    }

    //lambda function from util.function package
    public static List<Student> utilFilterStudents(Student newStudent)
    {
        manageStudentList.addStudent(newStudent);
        //if(filter == "Yes"){
        Predicate<Student> filterStudent = student -> student.getAge() >= 18;
        List<Student> filteredStudents = manageStudentList.filterStudents(filterStudent);

        //for (Student student : filteredStudents) {
          //  System.out.println(student.getName());
        //}//}
        return filteredStudents;
    }

    public static void utilProcessStudents()
    {
        //using consumer
        Consumer<Student> printStudentName = student -> logger.info("Student Name: " + student.getName());
        manageStudentList.getStudentNames(printStudentName);

        //using BiFunction
        BiFunction<Student, Integer, String> studentInfo = (student, age) -> "Student Age: " + student.getAge() + ", Name: " + student.getName();

        manageStudentList.processStudents(studentInfo);
    }

    public static List<Student> filterName(String letter)
    {
        Filter<Student> filter = student -> student.getName().startsWith(letter);
        List<Student> filteredStudents = manageStudentList.getStudentsWithFilter(filter);

        return filteredStudents;
    }

    public static List<Student> filterAge(int min,int max)
    {
        Filter<Student> filter = student -> student.getAge() >= min && student.getAge() <= max;
        List<Student> filteredStudents = manageStudentList.getStudentsWithFilter(filter);

        return filteredStudents;
    }

    public static List<Student> filterMajor(String major)
    {
        Filter<Student> filter = student -> student.getMajor().equals(major);
        List<Student> filteredStudents = manageStudentList.getStudentsWithFilter(filter);

        return filteredStudents;
    }

    public static List<Student> getManageStudentsList()
    {
        return manageStudentList.getStudents();
    }
}
