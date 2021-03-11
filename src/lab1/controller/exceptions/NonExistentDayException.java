package lab1.controller.exceptions;

public class NonExistentDayException extends RuntimeException {

    public NonExistentDayException(String message) {
        super(message);
    }

}
