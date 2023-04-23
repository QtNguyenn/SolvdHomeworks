public class Student extends Person{
   
    private String academicStanding;
    private String major;

    public Student(String name, String streetAddress,int age, int id,int phoneNumber, String academicStanding, String major)
    {
        super(name, streetAddress,age,id,phoneNumber);
        this.academicStanding = academicStanding;
        this.major = major;
    }
    
    public String getAcademicStanding()
    {
        return academicStanding;
    }
    
    public String getMajor()
    {
        return major;
    }
    public void setMajor (String major)
    {
        this.major = major;
    }
    public void setStanding(String academicaStanding)
    {
        this.academicStanding = academicaStanding;
    }
}

