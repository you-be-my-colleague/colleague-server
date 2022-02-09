package youBeMyColleague.study.advice.exception;

public class WishListCreateExcetion extends RuntimeException{
    public WishListCreateExcetion() {
    }

    public WishListCreateExcetion(String message) {
        super(message);
    }
}
