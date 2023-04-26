package People;
import System.Schedule;

public class Student extends Person{
   
    private String academicStanding;
    private String major;
    private Schedule schedule;
    private final String role = "Student";
    public Student(Schedule schedule)
    {
        this.schedule = schedule;
    }
    public Student(String name, String streetAddress,int age, int id,int phoneNumber,String schoolName,String schoolAddress, 
                    String academicStanding, String major, Schedule schedule)
    {
        super(name, streetAddress,age,id,phoneNumber,schoolName,schoolAddress);
        this.academicStanding = academicStanding;
        this.major = major;
        this.schedule = schedule;
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

}

