package youBeMyColleague.study.advice.exception;


public class EmptyValueException extends RuntimeException{

    public EmptyValueException() {
    }

    public EmptyValueException(String message) {
        super(message);
    }
}
