package com.university.exception;

/**
 * Checked exception for when a course reaches capacity
 * 
 * OOP Features Demonstrated:
 * - Checked exceptions
 */
public class CourseFullException extends EnrollmentException {
    
    private final String courseCode;
    private final int capacity;

    public CourseFullException(String courseCode, int capacity) {
        super(String.format("Course %s is full (capacity: %d)", courseCode, capacity));
        this.courseCode = courseCode;
        this.capacity = capacity;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public int getCapacity() {
        return capacity;
    }
}