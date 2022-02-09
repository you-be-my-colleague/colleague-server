package youBeMyColleague.study.advice.exception;

public class WishListCanNotDeleteException extends RuntimeException{
    public WishListCanNotDeleteException() {
    }

    public WishListCanNotDeleteException(String message) {
        super(message);
    }
}
