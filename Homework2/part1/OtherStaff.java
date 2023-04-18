public class OtherStaff extends Staff{
    private String staffTitle;
    private String duties;

    public OtherStaff(String name, String streetAddress,int age,int id,int phoneNumber,
                    String department, String staffTitle,String duties)
    {
        super(name, streetAddress,age,id,phoneNumber,department);
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
}
