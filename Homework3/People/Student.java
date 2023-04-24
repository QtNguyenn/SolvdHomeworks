package People;
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


     //implement abstract method from person class
     @ Override
     public String getRole()
     {
         return "Student";
     }

     //implement abstract method from person class to print info
     @Override
     public void printInfo()
     {
        System.out.println("Student's Info:");
        System.out.println("Name: "+ name);
        System.out.println("Address: "+ streetAddress);
        System.out.println("Age: "+ age);
        System.out.println("ID: "+ id);
        System.out.println("Phone Number : "+ phoneNumber);
     }
}

