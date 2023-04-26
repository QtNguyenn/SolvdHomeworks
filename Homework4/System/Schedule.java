package System;
public final class Schedule implements Course {
    
    private String courseTitle;
    private String courseDetails;
    private String startDate;
    private String endDate;

    public Schedule()
    {
        courseTitle = "Test Automation";
        courseDetails = "None";
        startDate = "";
        endDate = "";
        
    }
    public Schedule ( String courseTitle, String courseDetails, String startDate,String endDate)
    {
        this.courseTitle = courseTitle;
        this.courseDetails = courseDetails;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public String getCourseTitle()
    {
        return courseTitle;
    }
    public String getCourseDescription()
    {
        return courseDetails;
    }
    public String getStartDate()
    {
        return startDate;
    }
    public String getEndDate()
    {
        return endDate;
    }
    public void setCourseTitle(String courseTitle)
    {
        this.courseTitle = courseTitle;
    }
    public void setCourseDescription(String courseDetails)
    {
        this.courseDetails = courseDetails;
    }
    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }
    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }
    public final void printSchedule()
    {
        System.out.println("Course Title: " + getCourseTitle());
        System.out.println("Course Description: " + getCourseDescription());
        System.out.println("Start Time: " + getStartDate());
        System.out.println("End Time: " + getEndDate());
    }
}
