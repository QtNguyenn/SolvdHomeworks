import People.*;
import System.*;
public class Main {
    public static void main(String[] args)
    {
        
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
        System.out.println("Role: " + s.getRole());
        //Test abstract method
        s.printInfo();
        
        //Testing Professor class
        Professor t = new Professor("Tim", "987 ZXC Avenue", 50, 342234, 97323970, "CS", "CS101");
        System.out.println("\nTesting Professor Class");
        System.out.println("Name: " + t.getName());
        System.out.println("Street: " + t.getStreetAddress());
        System.out.println("Age: " + t.getAge());
        System.out.println("ID: " + t.getId());
        System.out.println("Phone: " + t.getPhoneNumber());
        System.out.println("Department: " + t.getDepartment());
        System.out.println("Major: " + t.getDepartment());
        System.out.println("Course: " + t.getCourse());
        System.out.println("Role: " + t.getRole());
        //Test abstract method
        t.printInfo();

        //Testing staff class
        Staff st = new Staff("Tim", "987 ZXC Avenue", 50, 342234, 97323970, "Financial");
        System.out.println("\nTesting Staff Class");
        System.out.println("Name: " + st.getName());
        System.out.println("Street: " + st.getStreetAddress());
        System.out.println("Age: " + st.getAge());
        System.out.println("ID: " + st.getId());
        System.out.println("Phone: " + st.getPhoneNumber());
        System.out.println("Department: " + st.getDepartment()); 
        System.out.println("Role: " + st.getRole());
        //Test abstract method
        st.printInfo();

        //Testing otherStaff class
        OtherStaff os = new OtherStaff("Josh", "927 wweXC Avenue", 35, 3122124, 9746570, "Financial", "Accountant", "Accounting");
        System.out.println("\nTesting OtherStaff Class");
        System.out.println("Name: " + os.getName());
        System.out.println("Street: " + os.getStreetAddress());
        System.out.println("Age: " + os.getAge());
        System.out.println("ID: " + os.getId());
        System.out.println("Phone: " + os.getPhoneNumber());
        System.out.println("Department: " + os.getDepartment()); 
        System.out.println("Title: " + os.getTitle());
        System.out.println("Duties: " + os.getDuties());
        System.out.println("Role: " + t.getRole());
        os.printInfo();

        //Testing building class
        Building bd = new Building("Science", 30);
        System.out.println("\nTesting building class.");
        System.out.println("Building: " + bd.getBuildingName());
        System.out.println("Number of room: " + bd.getNumberOfRoom());

        //Testing department class
        Department d = new Department("Science", 32, 200);
        System.out.println("\nTesting department class.");
        System.out.println("Department: " + d.getDepartmentName());
        System.out.println("Number of staff:" + d.getNumberOfStaff());
        System.out.println("Number of student:" + d.getNumberOfStudent());

        //Testing schedule/course classes
        Course c = new Course("Solvd", "Remote","ABC@solvd.com", 18, 04, 2023, "Test Au");
        System.out.println("\nTesting schedule and course classes");
        System.out.println("School name: " + c.schoolName);
        System.out.println("Address: " + c.address);
        System.out.println("Date: " + c.getDate()+"/"+c.getMonth()+"/"+c.getYear());
        System.out.println("Course name: " + c.getCourseName());
        
        //Testing full student's info
        System.out.println("\nTesting full student's info");
        Student ss = new Student("Bryan","123 ABC Avenue",
        30,123456,1234567890,"Good","Computer Science");
        Course cc = new Course("Solvd Inc","Remote","ABC@solvd.com",4,23,2023,"Test Automation");
        ss.printInfo();
        System.out.println("School name: " + cc.schoolName);
        System.out.println("Address: " + cc.address);
        System.out.println("Date: " + cc.getDate()+"/"+cc.getMonth()+"/"+cc.getYear());
        System.out.println("Course name: " + cc.getCourseName());

        //Test toString, hashCode, and equals
        // Create a few Student objects for testing
        Student student1 = new Student("Alice","123 ABC Avenue",
        25,1234,1234567890,"Good","Computer Science");
        Student student2 = new Student("Bob","12313 ABC Avenue",
        35,1234,1234567890,"Average","Math");
        Student student3 = new Student("Alice","123 ABC Avenue",
        25,1234,1234567890,"Good","Computer Science");
        
        // Test the toString() method
        System.out.println("\nTesting toString");
        System.out.println("student1.toString(): " + student1.toString());
        System.out.println("\nstudent2.toString(): " + student2.toString());
        System.out.println("\nstudent3.toString(): " + student3.toString());
        
        // Test the hashCode() method
        System.out.println("\nTesting hashCode");
        System.out.println("student1.hashCode(): " + student1.hashCode());
        System.out.println("student2.hashCode(): " + student2.hashCode());
        System.out.println("student3.hashCode(): " + student3.hashCode());
        
        // Test the equals() method
        System.out.println("\nTesting equals");
        System.out.println("student1.equals(student2): " + student1.equals(student2));
        System.out.println("student1.equals(student3): " + student1.equals(student3));
       

    }
}
