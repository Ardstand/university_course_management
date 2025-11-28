package com.university.exception;

/**
 * Unchecked Exception demonstrating:
 * - Custom runtime exceptions (extends RuntimeException)
 * - Unchecked exceptions (don't need to be declared or caught)
 * 
 * OOP Features Demonstrated:
 * - Unchecked exceptions
 * - RuntimeException inheritance
 */
public class InvalidGradeException extends RuntimeException {
    
    /**
     * Constructor with message
     */
    public InvalidGradeException(String message) {
        super(message);
    }

    /**
     * Constructor with message and cause
     */
    public InvalidGradeException(String message, Throwable cause) {
        super(message, cause);
    }
}