package youBeMyColleague.study.advice;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import youBeMyColleague.study.advice.exception.UserNotFoundException;
import youBeMyColleague.study.model.Fail;

import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class ExceptionController {


    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Fail> defaultException(Exception e) {
        return new ResponseEntity<>(new Fail("알수없는 오류입니다. 관리자 문의 부탁드립니다."), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected @ResponseBody ResponseEntity<Fail> UserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(new Fail("해당 유저아이디를 가진 계정이 존재하지 않습니다."), HttpStatus.BAD_REQUEST);
    }



}
