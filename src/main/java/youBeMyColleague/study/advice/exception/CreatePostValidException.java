package youBeMyColleague.study.advice.exception;

public class CreatePostValidException extends RuntimeException{

    public CreatePostValidException(String message) {
        super(message);
    }
    public CreatePostValidException() {
        super();
    }
}
