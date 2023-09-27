package com.pragyan.authapp.authapp.entity;

import com.pragyan.authapp.authapp.exceptions.ResourceNotFoundException;
import com.pragyan.authapp.authapp.payloads.ApiResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false,length = 100)
    private String name;
    private String email;
    private String password;
    private String about;

    @RestControllerAdvice
    public static class GlobalExceptionHandler {

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<?> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
            String message = ex.getMessage();
            ApiResponse apiResponse = new ApiResponse(message, false);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
             Map<String, String> resp = new HashMap<>();
             ex.getBindingResult().getAllErrors().forEach((error -> {
                String fieldName = ((FieldError)error).getField();
                String message = error.getDefaultMessage();
                resp.put(fieldName, message);
             }));
        return new ResponseEntity<Map<String, String>> (resp, HttpStatus.BAD_REQUEST);
        }
    }
}
