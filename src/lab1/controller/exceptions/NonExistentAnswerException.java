package lab1.controller.exceptions;

public class NonExistentAnswerException extends Exception{

    public NonExistentAnswerException(String message) {
        super(message);
    }

}
