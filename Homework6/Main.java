
//package src;
import src.People.*;
import src.System.*;
import java.util.HashMap;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    //Define a logger instance
    private static final Logger logger = LogManager.getLogger(Main.class);    
    public static void main(String[] args)
    {
        //Testing Student class
        logger.info("Testing Student Class.");
        Schedule s1 = new Schedule("Cs101","Intro to CS","4/10/2023","9/10/2023");
        Student student1 = new Student("Alice","123 ABC Avenue",
        25,1234,1234567890,"Solvd","Remote","Good","Computer Science",s1);
        CollegeManagement.manageStudent(student1);

        //Testing Student class
        Schedule s2 = new Schedule("Cs101","Intro to CS","4/10/2023","9/10/2023");
        Student student2 = new Student("James","123 ABC Avenue",
        25,5555,1234567890,"Solvd","Remote","Good","Computer Science",s2);
        CollegeManagement.manageStudent(student2);

        //Testing Student class
        Schedule s3 = new Schedule("Cs102","Intro to CS","4/10/2023","9/10/2023");
        Student student3 = new Student("John","123 ABC Avenue",
        25,9876,1234567890,"Solvd","Remote","Good","Computer Science",s3);
        CollegeManagement.manageStudent(student3);


        //Testing Professor class
        logger.info("Testing Professor Class.");
        Professor t1 = new Professor("Tim", "92317 ZdsadC Avenue", 50, 123456, 1223970,"Solvd","Remote", "CS", "CS101");
        CollegeManagement.manageProfessor(t1);
        Professor t2 = new Professor("Brook", "32123 ZXC Avenue", 30, 398765, 97323970,"Solvd","Remote", "SC", "SC101");
        CollegeManagement.manageProfessor(t2);
        Professor t3 = new Professor("Jess", "1234 CCC Avenue", 39, 239856, 973223970,"Solvd","Remote", "CS", "CS101");
        CollegeManagement.manageProfessor(t3);

        //test hashmap
        logger.info("Testing HashMap.");
        HashMap<Integer,Professor> professorMap = ManageProfessor.searchData();
        Professor professorWithId1234 = professorMap.get(123456);
        if (professorWithId1234 != null) {
            //System.out.println("Professor with ID 12345: " + professorWithId1234.getName());
            logger.info("Professor with ID 12345: " + professorWithId1234.getName());
        } else {
            //System.out.println("Professor with ID 123456 not found");
            logger.info("Professor with ID 123456 not found");
        }

        //test queue
        logger.info("Testing Queue");
        Queue<Professor> professorQueue = ManageProfessor.testQueue();
        //ManageProfessor.professorQueue = professorQueue;
        //System.out.println("Students in the queue:");
        logger.info("Students in the queue:");
        while (!professorQueue.isEmpty()) 
        {
            Professor professor = professorQueue.remove();
            //System.out.println(professor.getName());
            logger.info(professor.getName());
        }

        //test generic linkledlist
        logger.info("Testing Generic Custom LinkedList");
        Schedule ss1 = new Schedule("Cs101","Intro to CS","4/10/2023","9/10/2023");
        Student sstudent1 = new Student("James","123 ABC Avenue",
        25,122334,1234567890,"Solvd","Remote","Good","Computer Science",ss1);
        CollegeManagement.manageStudent(sstudent1);

        //Testing Student class
        Schedule ss2 = new Schedule("Cs101","Intro to CS","4/10/2023","9/10/2023");
        Student sstudent2 = new Student("Timmys","123 ABC Avenue",
        25,552355,1234567890,"Solvd","Remote","Good","Computer Science",ss2);
        CollegeManagement.manageStudent(sstudent2);

        //Testing Student class
        Schedule ss3 = new Schedule("Cs102","Intro to CS","4/10/2023","9/10/2023");
        Student sstudent3 = new Student("Pete","123 ABC Avenue",
        25,932876,1234567890,"Solvd","Remote","Good","Computer Science",ss3);
        CollegeManagement.manageStudent(sstudent3);

    }
}
