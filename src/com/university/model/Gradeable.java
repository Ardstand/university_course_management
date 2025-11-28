package com.university.model;

import java.util.List;

/**
 * Interface demonstrating:
 * - Abstract methods
 * - Default methods
 * - Static methods
 * - Private methods (Java 9+)
 * - Private static methods (Java 9+)
 * 
 * OOP Features Demonstrated:
 * - Interface methods (all types)
 */
public interface Gradeable {
    
    // Abstract methods
    double getGPA();
    List<Grade> getGrades();
    
    /**
     * Default method calculating total grade points
     */
    default double getTotalGradePoints() {
        return getGrades().stream()
            .mapToDouble(Grade::getGradePoint)
            .sum();
    }
    
    /**
     * Default method using private helper
     */
    default String getAcademicStanding() {
        double gpa = getGPA();
        return determineStanding(gpa);
    }
    
    /**
     * Default method checking if student is on honor roll
     */
    default boolean isHonorRoll() {
        return getGPA() >= 3.5;
    }
    
    /**
     * Static utility method to calculate GPA from grades
     */
    static double calculateGPA(List<Grade> grades) {
        if (grades == null || grades.isEmpty()) {
            return 0.0;
        }
        
        double totalPoints = grades.stream()
            .mapToDouble(Grade::getGradePoint)
            .sum();
        
        return roundToTwoDecimals(totalPoints / grades.size());
    }
    
    /**
     * Static method to check if GPA is passing
     */
    static boolean isPassingGPA(double gpa) {
        return gpa >= 2.0;
    }
    
    /**
     * Private method (Java 9+) - helper for default methods
     * Cannot be accessed outside this interface
     */
    private String determineStanding(double gpa) {
        if (gpa >= 3.8) return "DEAN'S LIST";
        if (gpa >= 3.5) return "HONOR ROLL";
        if (gpa >= 3.0) return "GOOD STANDING";
        if (gpa >= 2.0) return "SATISFACTORY";
        return "ACADEMIC PROBATION";
    }
    
    /**
     * Private static method (Java 9+)
     * Helper method for static methods
     */
    private static double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}