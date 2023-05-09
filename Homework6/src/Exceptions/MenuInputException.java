package src.Exceptions;

public class MenuInputException extends Exception{
     
    public MenuInputException()
    {
        super("Invalid input");
    }

    public MenuInputException(String message)
    {
        super(message);
    }

}
