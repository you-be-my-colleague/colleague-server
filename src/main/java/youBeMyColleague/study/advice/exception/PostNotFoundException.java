package youBeMyColleague.study.advice.exception;


public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(String msg) {
        super(msg);
    }

    public PostNotFoundException() {
        super();
    }
}
