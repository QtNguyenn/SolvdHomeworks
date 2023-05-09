package src.People;
import java.util.Objects;
public class Professor extends Person{
    private String department;
    private String teachingCourse;
    private final String role = "Professor";

    public Professor(String name, String streetAddress,int age,int id,int phoneNumber,
                    String schoolName, String schoolAddress,String department,String teachingCourse)
    {
        super(name, streetAddress,age,id,phoneNumber,schoolName,schoolAddress);
        this.department = department;
        this.teachingCourse = teachingCourse;
    }
    public String getDepartment()
    {
        return department;
    }
    public String getCourse()
    {
        return teachingCourse;
    }

    public void setDepartment( String department)
    {
        this.department = department;
    }
    public void setCourse( String teachingCourse)
    {
        this.teachingCourse = teachingCourse;
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
    }

    //toString method
    @Override
    public String toString()
    {
        return String.format("Name: %s%nAddress: %s%nAge: %d%nID: %d%nPhone: %d%nDepartment: %s%nTeaching Course: %s",
        name,streetAddress,age,id,phoneNumber,department,teachingCourse);
    }

    //hashcode method
    @Override
    public int hashCode()
    {
        int result = 17;

        result = 31 * result + name.hashCode();
        result = 31 * result + streetAddress.hashCode();
        result = 31 * result + age;
        result = 31 * result + id;
        result = 31 * result + phoneNumber;
        result = 31 * result + department.hashCode();
        result = 31 * result + teachingCourse.hashCode();
        return result;
    }

    //equals method
    @Override
    public boolean equals (Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if(!(obj instanceof Person))
        {
            return false;
        }

        Professor otherProf = (Professor) obj;
        return Objects.equals(name, otherProf.name) &&
               Objects.equals(streetAddress, otherProf.streetAddress) &&
               age == otherProf.age &&
               id == otherProf.id &&
               phoneNumber == otherProf.phoneNumber &&
               Objects.equals(department, otherProf.department) &&
               Objects.equals(teachingCourse, otherProf.teachingCourse);
    }
}
