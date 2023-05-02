package src.System;

public class Department {
    private String departmentName;
    private static int numberOfStaff = 0;
    private static int numberOfStudent = 0;
    static
    {
        numberOfStaff = 0;
        numberOfStudent = 0;
    }
    
    public Department( String departmentName)
    {
        this.departmentName = departmentName;
    }
    public static void addStudent(int numberStudent)
    {
        numberOfStudent+= numberStudent;
    }
    public static void addStaff(int numberStaff)
    {
        numberOfStaff+= numberStaff;
    }
    public String getDepartmentName()
    {
        return departmentName;
    }
    public static int getNumberOfStaff()
    {
        return numberOfStaff;
    }
    public static int getNumberOfStudent()
    {
        return numberOfStudent;
    }
    public void setDepartmentName(String DepartmentName)
    {
        this.departmentName = DepartmentName;
    }
    
}
