package youBeMyColleague.study.advice.exception;

public class WishListCanNotDeleteException extends RuntimeException{
    public WishListCanNotDeleteException() {
        super();
    }

    public WishListCanNotDeleteException(String message) {
        super(message);
    }
}
