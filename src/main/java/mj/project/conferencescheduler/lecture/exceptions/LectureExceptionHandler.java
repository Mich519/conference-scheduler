package mj.project.conferencescheduler.lecture.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LectureExceptionHandler {

    @ExceptionHandler(LectureNotFoundException.class)
    public ResponseEntity<?> handleLectureNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(LectureUserLimitException.class)
    public ResponseEntity<?> handleLectureUserLimitException() {
         return new ResponseEntity<>("Lecture is full", HttpStatus.CONFLICT);
    }
}
