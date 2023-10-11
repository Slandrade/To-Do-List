package br.com.slandrade.todolist.domain.exception.advice;

import br.com.slandrade.todolist.domain.exception.UserAlreadyExistException;
import br.com.slandrade.todolist.domain.exception.custom.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(UserAlreadyExistException.class)
  public ResponseEntity<CustomErrorResponse> handleUserAlreadyExistException(UserAlreadyExistException ex) {
    CustomErrorResponse errorResponse = new CustomErrorResponse();
    errorResponse.setTimestamp(LocalDateTime.now());
    errorResponse.setStatus(HttpStatus.CONFLICT.value());
    errorResponse.setError("Conflict");
    errorResponse.setMessage(ex.getMessage());
    errorResponse.setPath("/users/");

    return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
  }
}
