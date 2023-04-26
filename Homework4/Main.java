import People.*;
import System.*;
public class Main {
    public static void main(String[] args)
    {
        
        //Testing Student class
        Schedule s1 = new Schedule("Cs101","Intro to CS","4/10/2023","9/10/2023");
        Student s = new Student("Alice","123 ABC Avenue",
        25,1234,1234567890,"Solvd","Remote","Good","Computer Science",s1);
        System.out.println("\nTesting Student Class");
        System.out.println("Name: " + s.getName());
        System.out.println("Street: " + s.getStreetAddress());
        System.out.println("Age: " + s.getAge());
        System.out.println("ID: " + s.getId());
        System.out.println("Phone: " + s.getPhoneNumber());
        System.out.println("Standing: " + s.getAcademicStanding());
        System.out.println("Major: " + s.getMajor());
        //Test abstract method
        s.printInfo();
        
        //Testing Professor class
        Professor t = new Professor("Tim", "987 ZXC Avenue", 50, 342234, 97323970,"Solvd","Remote", "CS", "CS101");
        System.out.println("\nTesting Professor Class");
        System.out.println("Name: " + t.getName());
        System.out.println("Street: " + t.getStreetAddress());
        System.out.println("Age: " + t.getAge());
        System.out.println("ID: " + t.getId());
        System.out.println("Phone: " + t.getPhoneNumber());
        System.out.println("Department: " + t.getDepartment());
        System.out.println("Major: " + t.getDepartment());
        System.out.println("Course: " + t.getCourse());
        //Test abstract method
        t.printInfo();

        //Testing staff class
        Staff st = new Staff("Tim", "987 ZXC Avenue", 50, 342234, 97323970,"Solvd","Remote", "Financial");
        System.out.println("\nTesting Staff Class");
        System.out.println("Name: " + st.getName());
        System.out.println("Street: " + st.getStreetAddress());
        System.out.println("Age: " + st.getAge());
        System.out.println("ID: " + st.getId());
        System.out.println("Phone: " + st.getPhoneNumber());
        System.out.println("Department: " + st.getDepartment()); 
        //Test abstract method
        st.printInfo();

        //Testing otherStaff class
        OtherStaff os = new OtherStaff("Josh", "927 wweXC Avenue", 35, 3122124, 9746570,"Solvd","remote", "Financial", "Accountant", "Accounting");
        System.out.println("\nTesting OtherStaff Class");
        System.out.println("Name: " + os.getName());
        System.out.println("Street: " + os.getStreetAddress());
        System.out.println("Age: " + os.getAge());
        System.out.println("ID: " + os.getId());
        System.out.println("Phone: " + os.getPhoneNumber());
        System.out.println("Department: " + os.getDepartment()); 
        System.out.println("Title: " + os.getTitle());
        System.out.println("Duties: " + os.getDuties());
        os.printInfo();

        //Testing building class
        Building bd = new Building("Science", 30);
        System.out.println("\nTesting building class.");
        System.out.println("Building: " + bd.getBuildingName());
        System.out.println("Number of room: " + bd.getNumberOfRoom());

        //Testing department class
        Department d = new Department("Science");
        System.out.println("\nTesting department class.");
        System.out.println("Department: " + d.getDepartmentName());
        //Test static block to initialize number of staff and student.
        System.out.println("Number of staff:" + Department.getNumberOfStaff());
        System.out.println("Number of student:" + Department.getNumberOfStudent());
        //Add student and staff using static method
        System.out.println("Adding students and staff.");
        Department.addStudent(10);
        Department.addStaff(25);
        System.out.println("Number of staff:" + Department.getNumberOfStaff());
        System.out.println("Number of student:" + Department.getNumberOfStudent());
        
        //Testing schedule/course classes
        Schedule c = new Schedule("Test Automation", "Training","4/10/2023","9/10/2023");
        System.out.println("\nTesting schedule and course classes");
        System.out.println("Course name: " + c.getCourseTitle());
        System.out.println("Details: " + c.getCourseDescription());
        System.out.println("Date: " + c.getStartDate()+" - " +c.getEndDate());

        
    
        //Test toString, hashCode, and equals
        // Create a few Student objects for testing
        Schedule schedule = new Schedule("Cs101","Intro to CS","4/10/2023","9/10/2023");
        Student student1 = new Student("Alice","123 ABC Avenue",
        25,1234,1234567890,"Solvd","Remote","Good","Computer Science",schedule);
        Student student2 = new Student("Bob","12313 ABC Avenue",
        35,1234,1234567890,"Online Course","Remote","Average","Math",schedule);
        Student student3 = new Student("Alice","123 ABC Avenue",
        25,1234,1234567890,"Solvd","Remote","Good","Computer Science",schedule);
        
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
