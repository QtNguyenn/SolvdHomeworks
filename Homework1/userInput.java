import java.util.Scanner;

public class userInput {
    
    Scanner sc = new Scanner(System.in);
    public static void main (String args[])
    {
        System.out.println(getInfo());
    }

    public static String getInfo ()
    {
        Scanner sc = new Scanner(System.in);
        String name;
        int age;
        String hobby;

        System.out.println("Enter your name: ");
        name = sc.next();

        System.out.println("Enter your age: ");
        age = sc.nextInt();

        System.out.println("Enter your hobby: ");
        hobby = sc.next();

        String userInfo = "Name: " + name + "\nAge: " +age + "\nHobby: " + hobby;
        sc.close();
        return userInfo;
    }
}

