package exceptions;

public class InvalidCommandException extends Exception{
    public InvalidCommandException(String name) {
        super(name);
    }
}
