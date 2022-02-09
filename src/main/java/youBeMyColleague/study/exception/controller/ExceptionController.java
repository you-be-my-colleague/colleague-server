package youBeMyColleague.study.exception.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import youBeMyColleague.study.exception.NoItemError;
import youBeMyColleague.study.exception.PathFormatError;

import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(NoSuchElementException.class)
    public @ResponseBody NoItemError noItemErrorHandler(NoSuchElementException e){
        NoItemError noItem = new NoItemError();
        noItem.setMessage("Item Not Exists!");
        return noItem;
    }

    @ExceptionHandler(NumberFormatException.class)
    public @ResponseBody
    PathFormatError noItemErrorHandler(NumberFormatException e){
        PathFormatError formatError = new PathFormatError();
        formatError.setMessage("Invalid Path Variable!");
        return formatError;
    }



}
