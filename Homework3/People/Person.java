package People;
import java.util.Objects;
public abstract class Person {

    protected String name;
    protected String streetAddress;
    protected int age;
    protected int id;
    protected int phoneNumber;

    public Person()
    {
        name = "";
        streetAddress = "";
        age = 0;
        id = 0000;
        phoneNumber = 0000;
    }
    public Person( String name, String streetAddress, int age, int id, int phoneNumber)
    {
        this.name = name;
        this.streetAddress = streetAddress;
        this.age = age;
        this.id= id;
        this.phoneNumber = phoneNumber;
    }
    public String getName()
    {
        return name;
    }
    public String getStreetAddress()
    {
        return streetAddress;
    }
    public int getAge()
    {
        return age;
    }
    public int getId()
    {
        return id;
    }
    public int getPhoneNumber()
    {
        return phoneNumber;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setStreetAddress(String streetAddress)
    {
        this.streetAddress = streetAddress;
    }
    public void setAge(int age)
    {
        this.age = age;
    }
    public void setId( int id)
    {
        this.id= id;
    }
    public void setPhoneNumber( int phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    // abstract method for getting a person's role
    public abstract String getRole();
    //
    public abstract void printInfo();
    //toString method
    @Override
    public String toString()
    {
        return String.format("Name: %s%nAddress: %s%nAge: %d%nID: %d%nPhone: %d",
        name,streetAddress,age,id,phoneNumber);
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

        Person otherPerson = (Person) obj;
        return Objects.equals(name, otherPerson.name) &&
               Objects.equals(streetAddress, otherPerson.streetAddress) &&
               age == otherPerson.age &&
               id == otherPerson.id &&
               phoneNumber == otherPerson.phoneNumber;
    }
    
}
