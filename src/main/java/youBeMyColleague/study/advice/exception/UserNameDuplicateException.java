package youBeMyColleague.study.advice.exception;

public class UserNameDuplicateException extends RuntimeException{
    public UserNameDuplicateException() {
    }

    public UserNameDuplicateException(String message) {
        super(message);
    }
}
