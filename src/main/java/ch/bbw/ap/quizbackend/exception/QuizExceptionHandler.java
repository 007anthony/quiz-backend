package ch.bbw.ap.quizbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class QuizExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public IllegalArgumentException illegalArgumentException(IllegalArgumentException illegalArgumentException) {
        return illegalArgumentException;
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public NoSuchElementException noSuchElementException(NoSuchElementException noSuchElementException) {
        return noSuchElementException;
    }

}
