package com.isaacggr.todolist.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.mongodb.MongoException;
import com.mongodb.MongoTimeoutException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMostSpecificCause().getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException e) {
        var bindingResult = e.getBindingResult();
        var fieldError = bindingResult.getFieldError();
        var message = fieldError != null ? fieldError.getDefaultMessage() : "Erro de validação";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(MongoTimeoutException.class)
    public ResponseEntity<String> handleMongoTimeoutException(MongoTimeoutException e) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Erro de conexão com o banco de dados: " + e.getMessage());
    }

    @ExceptionHandler(MongoException.class)
    public ResponseEntity<String> handleMongoException(MongoException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro no banco de dados: " + e.getMessage());
    }
}