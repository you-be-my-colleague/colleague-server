package youBeMyColleague.study.advice.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String msg) {
        super(msg);
    }

    public UserNotFoundException() {
        super();
    }

}
