package youBeMyColleague.study.advice.exception;


public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException(String msg) {
        super(msg);
    }

    public CommentNotFoundException() {
        super();
    }
}
