
package src.System;
 
import java.util.Scanner;  //Import Scanner class to read user input

import src.Exceptions.MenuInputException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class CollegeManagement
{
    private static final Logger logger = LogManager.getLogger(CollegeManagement.class);

    public static void menu()
    {    
        Scanner scanner = new Scanner(System.in); // create scanner object to read user input

        int option = 0;
        //loadStudentData(FILENAME);
        while ( option != 5)
        {
            //printMenu();
            System.out.println("Welcome to College Management System!");
            System.out.println("1. Manage Students.");
            System.out.println("2. Manage Professors.");
            System.out.println("3. Manage Staffs.");
            System.out.println("Enter your choice: ");
            try{
                option = scanner.nextInt();
                if(option<1 || option > 5)
                {
                    throw new MenuInputException();
                }
            } catch (MenuInputException e)
            {
                System.out.println(e.getMessage());
                logger.error("Input value: {}", option);
                logger.error("Error: {}",e.getMessage());
            }
            switch(option)
            {
                case 1:
                    ManageStudent.printStudentMenu();
                    break;
                // case 2:
                //     ManageProfessor.printProfessorMenu();
                //     break;
                // case 3:
                //     ManageStaff.printStaffMenu();
                //     break;
                case 4:
                    System.out.println("Exiting program..");
                    break;
                default:
                    System.out.println("Invalid option. Please try again");
                    break;    
            }
        
        }
  
        scanner.close();
    }


}
