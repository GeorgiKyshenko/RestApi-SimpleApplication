package rest.app.springrest.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorDetails> notFoundException(UserNotFoundException exception) {
        log.error("User not found exception");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorDetails(LocalDateTime.now(), exception.getMessage(), "Type existing ID"));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDetails> errorHandler(MethodArgumentNotValidException exception) {
        log.error("Invalid input exception");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDetails(LocalDateTime.now(), Objects.requireNonNull(exception.getFieldError()).getDefaultMessage(), "Valid input: min size of name is 2 chars, date must be present"));
    }
}
