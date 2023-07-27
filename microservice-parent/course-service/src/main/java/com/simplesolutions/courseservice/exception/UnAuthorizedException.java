package com.simplesolutions.courseservice.exception;

/**
 * UnAuthorizedException extends {@link RuntimeException}
 *
 * @author Mufarrij
 * @since 24/7/2023
 */
public class UnAuthorizedException extends RuntimeException{
    public UnAuthorizedException(String message) {
        super(message);
    }
}
