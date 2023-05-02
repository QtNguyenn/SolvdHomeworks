package src.People;

public class OtherStaff extends Staff{
    private String staffTitle;
    private String duties;

    public OtherStaff(String name, String streetAddress,int age,int id,int phoneNumber,
                    String schoolName,String schoolAddress,String department, String staffTitle,String duties)
    {
        super(name, streetAddress,age,id,phoneNumber,schoolName,schoolAddress,department);
        this.staffTitle = staffTitle;
        this.duties = duties;
    }
    public String getTitle()
    {
        return staffTitle;
    }
    public String getDuties()
    {
        return duties;
    }

    public void setTitle( String staffTitle)
    {
        this.staffTitle = staffTitle;
    }
    public void setDuties( String duties)
    {
        this.duties = duties;
    }
    //implement abstract method from person class to print info
    @Override
    public void printInfo()
    {
       System.out.println(staffTitle+ "'s Info:");
       System.out.println("Name: "+ name);
       System.out.println("Address: "+ streetAddress);
       System.out.println("Age: "+ age);
       System.out.println("ID: "+ id);
       System.out.println("Phone Number : "+ phoneNumber);
    }
}
