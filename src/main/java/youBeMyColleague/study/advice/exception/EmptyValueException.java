package youBeMyColleague.study.advice.exception;


public class EmptyValueException extends RuntimeException{

    public EmptyValueException() {
        super();
    }

    public EmptyValueException(String message) {
        super(message);
    }

    public EmptyValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
