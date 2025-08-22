package exceptions;

public class MissingDeadlineArgumentException extends InvalidArugmentException {
    public MissingDeadlineArgumentException(String string) {
        super(string);
    }
}
