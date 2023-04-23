public class Main {
    public static void main(String[] args)
    {
        //Test Person class
        Person p = new Person("John","123 ABC Avenue",25,1234,1234567890);
        
        System.out.println("Testing Person Class");
        System.out.println("Name: " + p.getName());
        System.out.println("Age: " + p.getAge());

        //Testing Student class
        Student s = new Student("Alice","123 ABC Avenue",
        25,1234,1234567890,"Good","Computer Science");
        System.out.println("\nTesting Student Class");
        System.out.println("Name: " + s.getName());
        System.out.println("Street: " + s.getStreetAddress());
        System.out.println("Age: " + s.getAge());
        System.out.println("ID: " + s.getId());
        System.out.println("Phone: " + s.getPhoneNumber());
        System.out.println("Standing: " + s.getAcademicStanding());
        System.out.println("Major: " + s.getMajor());
      

        //Testing Professor class/Staff class

        Professor t = new Professor("Tim", "987 ZXC Avenue", 50, 342234, 97323970, "CS", "Professor", "CS101");
        System.out.println("\nTesting Professor Class");
        System.out.println("Name: " + t.getName());
        System.out.println("Street: " + t.getStreetAddress());
        System.out.println("Age: " + t.getAge());
        System.out.println("ID: " + t.getId());
        System.out.println("Phone: " + t.getPhoneNumber());
        System.out.println("Department: " + t.getDepartment()); //from staff
        System.out.println("Major: " + t.getTitle());
        System.out.println("Course: " + t.getCourse());
        System.out.println("Major: " + t.getTitle());
    

        //Testing otherStaff class

        OtherStaff os = new OtherStaff("Josh", "927 wweXC Avenue", 35, 3122124, 9746570, "Financial", "Accountant", "Accounting");
        System.out.println("\nTesting OtherStaff Class");
        System.out.println("Name: " + os.getName());
        System.out.println("Street: " + os.getStreetAddress());
        System.out.println("Age: " + os.getAge());
        System.out.println("ID: " + os.getId());
        System.out.println("Phone: " + os.getPhoneNumber());
        System.out.println("Department: " + os.getDepartment()); //from staff
        System.out.println("Title: " + os.getTitle());
        System.out.println("Duties: " + os.getDuties());
        

        //Testing building class
        Building bd = new Building("Science", 30);
        System.out.println("\nTesting building class.");
        System.out.println("Building: " + bd.getBuildingName());
        System.out.println("Number of room: " + bd.getNumberOfRoom());

        //Testing department class
        Department d = new Department("Science", 32, 200);
        System.out.println("\nTesting building class.");
        System.out.println("Department: " + d.getDepartmentName());
        System.out.println("Number of staff:" + d.getNumberOfStaff());
        System.out.println("Number of student:" + d.getNumberOfStudent());

        //Testing schedule/course classes
        Course c = new Course("Solvd", "Remote", 18, 04, 2023, "Test Automation");
        System.out.println("\nTesting schedule and course classes");
        System.out.println("School name: " + c.schoolName);
        System.out.println("Address: " + c.address);
        System.out.println("Date: " + c.getDate()+"/"+c.getMonth()+"/"+c.getYear());
        System.out.println("Course name: " + c.getCourseName());
        

    }
}
