package youBeMyColleague.study.advice.exception;

public class WishListNotFoundException extends RuntimeException{
    public WishListNotFoundException() {
    }

    public WishListNotFoundException(String message) {
        super(message);
    }
}
