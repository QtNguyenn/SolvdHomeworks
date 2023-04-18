public class Staff extends Person{

    private String department;
    public Staff()
    {
        this.department = "Academic";
    }
    public Staff(String name, String streetAddress,int age,int id,int phoneNumber,String department)
    {
        super(name, streetAddress,age,id,phoneNumber);
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
}
