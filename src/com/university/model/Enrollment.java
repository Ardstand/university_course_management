package com.university.model;

import java.time.LocalDate;

/**
 * Enrollment record demonstrating immutable data carriers
 * 
 * OOP Features Demonstrated:
 * - Records
 * - Immutability
 * - Date API usage
 */
public record Enrollment(
    String studentId,
    String courseCode,
    LocalDate enrollmentDate,
    Grade finalGrade
) {
    
    /**
     * Compact constructor with validation
     */
    public Enrollment {
        if (studentId == null || studentId.isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty");
        }
        if (courseCode == null || courseCode.isEmpty()) {
            throw new IllegalArgumentException("Course code cannot be null or empty");
        }
        if (enrollmentDate == null) {
            throw new IllegalArgumentException("Enrollment date cannot be null");
        }
    }
    
    /**
     * Alternative constructor without grade (for initial enrollment)
     */
    public Enrollment(String studentId, String courseCode, LocalDate enrollmentDate) {
        this(studentId, courseCode, enrollmentDate, null);
    }
    
    /**
     * Check if enrollment is graded
     */
    public boolean isGraded() {
        return finalGrade != null;
    }
    
    /**
     * Get enrollment status
     */
    public String getStatus() {
        if (finalGrade == null) {
            return "IN_PROGRESS";
        }
        return finalGrade.isPassing() ? "PASSED" : "FAILED";
    }
}