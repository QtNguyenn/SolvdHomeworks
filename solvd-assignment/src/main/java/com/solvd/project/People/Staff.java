package com.solvd.project.People;
import java.util.Objects;
public class Staff extends Person{

    private String department;
    private final String role = "Staff";
    public Staff()
    {
        this.department = "Academic";
    }
    public Staff(String name, String streetAddress,int age,int id,int phoneNumber,String schoolName,String schoolAddress,String department)
    {
        super(name, streetAddress,age,id,phoneNumber,schoolName,schoolAddress);
        this.department = department;
    }
    public String getDepartment()
    {
        return department;
    }
    public void setDepartment(String department)
    {
        this.department = department;
    }

    //implement abstract method from person class to print info
    @Override
    public void printInfo()
    {
       System.out.println(role+ " Info:");
       System.out.println("Name: "+ name);
       System.out.println("Address: "+ streetAddress);
       System.out.println("Age: "+ age);
       System.out.println("ID: "+ id);
       System.out.println("Phone Number : "+ phoneNumber);
    }

      //toString method
      @Override
      public String toString()
      {
          return String.format("Name: %s%nAddress: %s%nAge: %d%nID: %d%nPhone: %d%nDepartment: %s",
          name,streetAddress,age,id,phoneNumber,department);
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
  
          Staff staff = (Staff) obj;
          return Objects.equals(name, staff.name) &&
                 Objects.equals(streetAddress, staff.streetAddress) &&
                 age == staff.age &&
                 id == staff.id &&
                 phoneNumber == staff.phoneNumber &&
                 Objects.equals(department, staff.department);
      }
}
