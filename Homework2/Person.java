public class Person extends CollegeManagement{

    private String name;
    private String streetAddress;
    private int age;
    private int id;
    private int phoneNumber;

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
    
}
