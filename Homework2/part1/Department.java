public class Department {
    private String departmentName;
    private int numberOfStaff;
    private int numberOfStudent;
    public Department()
    {
        departmentName = "";
        numberOfStaff = 0;
        numberOfStudent = 0;
    }
    public Department( String departmentName, int numberOfStaff, int numberOfStudent)
    {
        this.departmentName = departmentName;
        this.numberOfStaff = numberOfStaff;
        this.numberOfStudent = numberOfStudent;
    }

    public String getDepartmentName()
    {
        return departmentName;
    }
    public int getNumberOfStaff()
    {
        return numberOfStaff;
    }
    public int getNumberOfStudent()
    {
        return numberOfStudent;
    }
    public void setDepartmentName(String DepartmentName)
    {
        this.departmentName = DepartmentName;
    }
    public void setNumberOfStaff(int numberOfStaff)
    {
        this.numberOfStaff = numberOfStaff;
    }
    public void setNumberOfStudent(int numberOfStudent)
    {
        this.numberOfStudent = numberOfStudent;
    }
}
