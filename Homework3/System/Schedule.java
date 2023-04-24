package System;
public class Schedule extends CollegeManagement {
    private int year;
    private int date;
    private int month;
    
    public Schedule()
    {
        this.date = 00;
        this.year = 0000;
        this.month = 00;
    }
    public Schedule (String schoolName, String address,String email, int date,int month, int year)
    {
        this.schoolName = schoolName;
        this.address = address;
        this.email = email;
        this.year = year;
        this.date = date;
        this.month = month;
    }

    public int getYear()
    {
        return year;
    }
    public int getDate()
    {
        return date;
    }
    public int getMonth()
    {
        return month;
    }
    public void setYear(int year)
    {
        this.year = year;
    }
    public void setMonth(int month)
    {
        this.month = month;
    }
    public void setDate(int date)
    {
        this.date = date;
    }
}
