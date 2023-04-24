
package System;
public class CollegeManagement {
    public String schoolName;
    public String address;
    protected String email;

    public CollegeManagement()
    {
        schoolName = "";
        address = "";
        email = "";
    }
    public CollegeManagement( String schoolName, String address, String email)
    {
        this.schoolName = schoolName;
        this.address = address;
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
}
