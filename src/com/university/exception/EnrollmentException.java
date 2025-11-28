package com.university.exception;

/**
 * Checked Exception demonstrating:
 * - Custom checked exceptions (extends Exception)
 * - Exception constructors
 * - Exception chaining
 * 
 * OOP Features Demonstrated:
 * - Checked exceptions
 * - Exception handling
 * - Constructor overloading
 */
public class EnrollmentException extends Exception {
    
    /**
     * Constructor with message only
     */
    public EnrollmentException(String message) {
        super(message);
    }

    /**
     * Constructor with message and cause (exception chaining)
     */
    public EnrollmentException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with cause only
     */
    public EnrollmentException(Throwable cause) {
        super(cause);
    }

    /**
     * Full constructor with all parameters
     */
    public EnrollmentException(String message, Throwable cause, 
                              boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}