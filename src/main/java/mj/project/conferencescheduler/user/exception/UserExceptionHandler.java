package mj.project.conferencescheduler.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(UsernameAlreadyInUseException.class)
    public ResponseEntity<?> handleUsernameAlreadyInUseException() {
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<?> handleEmailAlreadyInUseException() {
        return new ResponseEntity<>("Email is already taken!", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmailResponseFileCreationEexception.class)
    public ResponseEntity<?> handleResponseFileCreationException() {
        return new ResponseEntity<>("There was a problem with your reservation", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserLecturesDatesOverlapException.class)
    public ResponseEntity<?> handleUserLecturesDatesOverlap() {
        return new ResponseEntity<>("User lecture dates overlap", HttpStatus.BAD_REQUEST);
    }
}
