package com.simplesolutions.courseservice.exception;

/**
 * ValidationException extends {@link RuntimeException}
 *
 *
 * @author Mufarrij
 * @since 24/7/2023
 */
public class ValidationException extends RuntimeException{
    public ValidationException(String message) {
        super(message);
    }
}
