package System;

public class Course extends Schedule{
    
    private String courseName;

    public Course(String schoolName, String address,String email,int date, int month, int year, String courseName)
    {
        super( schoolName, address,email, date,  month,year);
        this.courseName = courseName;
    }

    public String getCourseName()
    {
        return courseName;
    }
    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }
}
