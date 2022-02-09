package youBeMyColleague.study.advice;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import youBeMyColleague.study.advice.exception.*;
import youBeMyColleague.study.model.Fail;
import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Fail> UserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(new Fail("해당 유저 아이디를 가진 계정이 존재하지 않습니다."), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNameDuplicateException.class)
    public ResponseEntity<Fail> UserNameDuplicateException(UserNameDuplicateException e) {
        return new ResponseEntity<>(new Fail("이미 존재하는 이메일 입니다."), HttpStatus.OK);
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<Fail> PostNotFoundException(PostNotFoundException e) {
        return new ResponseEntity<>(new Fail("해당 게시글이 존재하지 않습니다. 해당 게시글이 존재하지 않거나 잘못된 요청입니다."), HttpStatus.OK);
    }

    @ExceptionHandler(EmptyValueException.class)
    public ResponseEntity<Fail> EmptyValueException(HttpServletRequest request, EmptyValueException e) {
        return new ResponseEntity<>(new Fail("필수 작성 요소가 작성되지 않았습니다"), HttpStatus.OK);
    }

    @ExceptionHandler(WishListCanNotDeleteException.class)
    public ResponseEntity<Fail> WishListCanNotDeleteException(WishListCanNotDeleteException e) {
        return new ResponseEntity<>(new Fail("관심글을 삭제 할 수 없습니다. 해당 게시글이 존재하지 않거나 잘못된 요청입니다."), HttpStatus.OK);
    }

    @ExceptionHandler(WishListCreateException.class)
    public ResponseEntity<Fail> WishListCreateException(WishListCreateException e) {
        return new ResponseEntity<>(new Fail("관심글을 등록할 수 없습니다. 해당 게시글이 존재하지 않거나 잘못된 요청입니다."), HttpStatus.OK);
    }

    @ExceptionHandler(WishListNotFoundException.class)
    public ResponseEntity<Fail> WishListNotFoundException(WishListNotFoundException e) {
        return new ResponseEntity<>(new Fail("관심글을 찾을 수 없습니다. 해당 게시글이 존재하지 않거나 잘못된 요청입니다."), HttpStatus.OK);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Fail> defaultException(Exception e) {
        return new ResponseEntity<>(new Fail("알수없는 오류입니다. 관리자 문의 부탁드립니다."), HttpStatus.OK);
    }

}










