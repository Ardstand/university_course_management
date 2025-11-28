package com.university.model;

import java.time.LocalDate;

/**
 * Interface demonstrating:
 * - Abstract methods
 * - Default interface methods (Java 8+)
 * - Static interface methods (Java 8+)
 * - Private interface methods (Java 9+)
 * 
 * OOP Features Demonstrated:
 * - Interfaces
 * - Default methods
 * - Static methods in interfaces
 * - Private helper methods in interfaces
 */
public interface Enrollable {
    
    // Abstract methods - must be implemented by classes
    String getEnrollmentId();
    LocalDate getEnrollmentDate();
    boolean isActive();
    
    /**
     * Default interface method - provides default implementation
     * Can be overridden by implementing classes
     */
    default String getEnrollmentStatus() {
        return isActive() ? "ACTIVE" : "INACTIVE";
    }
    
    /**
     * Default method using private helper method
     */
    default String getFormattedEnrollmentInfo() {
        return formatEnrollmentDetails(
            getEnrollmentId(), 
            getEnrollmentDate(), 
            getEnrollmentStatus()
        );
    }
    
    /**
     * Default method with logic
     */
    default int getDaysSinceEnrollment() {
        return (int) java.time.temporal.ChronoUnit.DAYS.between(
            getEnrollmentDate(), 
            LocalDate.now()
        );
    }
    
    /**
     * Static interface method - utility method
     * Can be called without an instance
     */
    static boolean isValidEnrollmentId(String id) {
        return id != null && id.matches("^[A-Z0-9]{5,10}$");
    }
    
    /**
     * Static factory method
     */
    static String generateEnrollmentId(String prefix, int number) {
        if (prefix == null || prefix.isEmpty()) {
            throw new IllegalArgumentException("Prefix cannot be null or empty");
        }
        return String.format("%s%05d", prefix, number);
    }
    
    /**
     * Private interface method (Java 9+)
     * Used internally by default methods
     * Cannot be accessed by implementing classes
     */
    private String formatEnrollmentDetails(String id, LocalDate date, String status) {
        return String.format("Enrollment[ID=%s, Date=%s, Status=%s]", id, date, status);
    }
}