package com.simplesolutions.studentservice.exception;

/**
 * ResourceNotFoundException extends {@link RuntimeException}
 * thrown when unable to find specific resource
 *
 * @author Mufarrij
 * @since 23/7/2023
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}