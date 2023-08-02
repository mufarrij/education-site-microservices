package com.simplesolutions.studentservice.advice;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.simplesolutions.studentservice.exception.ExceptionResponse;
import com.simplesolutions.studentservice.exception.ResourceNotFoundException;
import com.simplesolutions.studentservice.exception.UnAuthorizedException;
import com.simplesolutions.studentservice.exception.ValidationException;

/**
 * Global Exception Handler
 *
 * @author Mufarrij
 * @since 24/7/2023
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ExceptionResponse response = ExceptionResponse.builder()
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST)
                .errorMessage(Arrays.asList(ex.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ExceptionResponse response = ExceptionResponse.builder()
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST)
                .errorMessage(ex.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList()))
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        ExceptionResponse response = ExceptionResponse.builder()
                .errorCode(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND)
                .errorMessage(Arrays.asList(ex.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<ExceptionResponse> handleResourceAccessException(Exception ex) {
        ExceptionResponse response = ExceptionResponse.builder()
                .errorCode(HttpStatus.BAD_GATEWAY.value())
                .error(HttpStatus.BAD_GATEWAY)
                .errorMessage(Arrays.asList(ex.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
        System.out.println(ex);
        return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<ExceptionResponse> handUnAuthorizedException(UnAuthorizedException ex) {
        ExceptionResponse response = ExceptionResponse.builder()
                .errorCode(HttpStatus.UNAUTHORIZED.value())
                .error(HttpStatus.UNAUTHORIZED)
                .errorMessage(Arrays.asList(ex.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionResponse> handValidationException(ValidationException ex) {
        ExceptionResponse response = ExceptionResponse.builder()
                .errorCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .error(HttpStatus.UNPROCESSABLE_ENTITY)
                .errorMessage(Arrays.asList(ex.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleRuntimeExceptions(RuntimeException ex) {
        ExceptionResponse response = ExceptionResponse.builder()
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorMessage(Arrays.asList(ex.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGeneralExceptions(Exception ex) {
        ExceptionResponse response = ExceptionResponse.builder()
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorMessage(Arrays.asList(ex.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
        System.out.println(ex);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
