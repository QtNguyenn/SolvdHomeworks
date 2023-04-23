public class Professor extends Staff{
    private String employeeTitle;
    private String teachingCourse;

    public Professor(String name, String streetAddress,int age,int id,int phoneNumber,
                    String department, String employeeTitle,String teachingCourse)
    {
        super(name, streetAddress,age,id,phoneNumber,department);
        this.employeeTitle = employeeTitle;
        this.teachingCourse = teachingCourse;
    }
    public String getTitle()
    {
        return employeeTitle;
    }
    public String getCourse()
    {
        return teachingCourse;
    }

    public void setTitle( String employeeTitle)
    {
        this.employeeTitle = employeeTitle;
    }
    public void setCourse( String teachingCourse)
    {
        this.teachingCourse = teachingCourse;
    }
}
