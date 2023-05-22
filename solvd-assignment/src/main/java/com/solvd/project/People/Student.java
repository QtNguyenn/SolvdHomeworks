package com.solvd.project.People;

import com.solvd.project.Exceptions.InvalidInputException;
import com.solvd.project.System.Schedule;
import com.solvd.project.System.Subject;

public class Student extends Person{
   
    private String academicStanding;
    private String major;
    private Schedule schedule;
    private Subject subject;
    private final String role = "Student";

    public Student(Schedule schedule)
    {
        this.schedule = schedule;
    }

    //constructor without schedule
    public Student(String name, String streetAddress,int age, int id,int phoneNumber,String schoolName,String schoolAddress, 
                    String academicStanding, String major) throws InvalidInputException
    {
        
        super(name, streetAddress,age,id,phoneNumber,schoolName,schoolAddress);
        if (major.equals("None"))
        {
            throw new InvalidInputException("major cannot be empty.");
        }
        this.academicStanding = academicStanding;
        this.major = major;
    }

    //constructor with schedule
    public Student(String name, String streetAddress,int age, int id,int phoneNumber,String schoolName,String schoolAddress, 
                    String academicStanding, String major, Schedule schedule)
    {
        super(name, streetAddress,age,id,phoneNumber,schoolName,schoolAddress);
        this.academicStanding = academicStanding;
        this.major = major;
        this.schedule = schedule;
    }
    
    //constructor for lambda homework uses
    public Student(String name, int age, String major)
    {
        super(name,age);
        this.major = major;
    }
    public Student(String name, int age, Subject subject)
    {
        super(name,age);
        this.subject = subject;
    }

    public String getAcademicStanding()
    {
        return academicStanding;
    }
    public String getMajor()
    {
        return major;
    }
    public Subject getSubject()
    {
        return subject;
    }
    public void setMajor (String major)
    {
        this.major = major;
    }
    public void setStanding(String academicaStanding)
    {
        this.academicStanding = academicaStanding;
    }
    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

     //implement abstract method from person class to print info
     @Override
     public void printInfo()
     {
        System.out.println(role+" Info:");
        System.out.println("Name: "+ name);
        System.out.println("Address: "+ streetAddress);
        System.out.println("Age: "+ age);
        System.out.println("ID: "+ id);
        System.out.println("Phone Number : "+ phoneNumber);
        System.out.println("School: "+ schoolName);
        System.out.println("School Address : "+ schoolAddress);
        schedule.printSchedule();
     }

     //toString method
    @Override
    public String toString()
    {
        return String.format("Name: %s%nAddress: %s%nAge: %d%nID: %d%nPhone: %d%nSchool Name: %s%nSchoolAddress: %s%nStanding: %s%nMajor: %s",
        name,streetAddress,age,id,phoneNumber,schoolName,schoolAddress,academicStanding,major);
    }

    // a custome toString for lambda homework uses
    public String customToString()
    {
        return String.format("Name: %s%nAge: %d%nMajor: %s", name, age,major);
    }
}

