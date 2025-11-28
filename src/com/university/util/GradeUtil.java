package com.university.util;

import com.university.model.Grade;
import com.university.exception.InvalidGradeException;

/**
 * Utility class demonstrating:
 * - Switch expressions (Java 14+)
 * - Pattern matching in switch (Java 21+)
 * - Static utility methods
 * - Varargs
 * 
 * OOP Features Demonstrated:
 * - Switch expressions
 * - Pattern matching
 * - Varargs
 * - Static methods
 */
public class GradeUtil {
    
    // Private constructor to prevent instantiation of utility class
    private GradeUtil() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Demonstrates switch expressions (Java 14+)
     * Returns grade description based on grade
     */
    public static String getGradeDescription(Grade grade) {
        // Switch expression - returns value directly
        return switch (grade) {
            case A_PLUS, A -> "Excellent";
            case A_MINUS, B_PLUS -> "Very Good";
            case B, B_MINUS -> "Good";
            case C_PLUS, C -> "Satisfactory";
            case C_MINUS -> "Minimum Pass";
            case D -> "Conditional Pass";
            case F -> "Fail";
        };
    }

    /**
     * Switch expression with yield for complex logic
     */
    public static String getGradeAdvice(Grade grade) {
        return switch (grade) {
            case A_PLUS, A -> {
                yield "Outstanding performance! Keep up the excellent work.";
            }
            case A_MINUS, B_PLUS -> {
                yield "Great job! You're doing very well.";
            }
            case B, B_MINUS -> {
                yield "Good work. Continue to apply yourself.";
            }
            case C_PLUS, C -> {
                yield "Satisfactory. Consider seeking additional help.";
            }
            case C_MINUS, D -> {
                yield "You're passing, but there's room for improvement.";
            }
            case F -> {
                yield "Please meet with your instructor and consider tutoring.";
            }
        };
    }

    /**
     * Pattern matching with switch (Java 21+)
     * Demonstrates type patterns in switch
     */
    public static String describeGradeValue(Object value) {
        // Pattern matching in switch
        return switch (value) {
            case null -> "No grade provided";
            case Grade g when g == Grade.A_PLUS -> "Perfect grade: " + g;
            case Grade g when g.isPassing() -> "Passing grade: " + g;
            case Grade g -> "Failing grade: " + g;
            case Integer i when i >= 90 -> "Excellent score: " + i;
            case Integer i when i >= 60 -> "Passing score: " + i;
            case Integer i -> "Failing score: " + i;
            case String s -> "Grade string: " + s;
            default -> "Unknown grade type: " + value.getClass().getSimpleName();
        };
    }

    /**
     * Calculate average grade using varargs
     * Demonstrates varargs
     */
    public static double calculateAverageGPA(Grade... grades) {  // Varargs
        if (grades == null || grades.length == 0) {
            return 0.0;
        }

        double total = 0.0;
        for (Grade grade : grades) {
            if (grade == null) {
                throw new InvalidGradeException("Cannot calculate average with null grade");
            }
            total += grade.getGradePoint();
        }

        return Math.round((total / grades.length) * 100.0) / 100.0;
    }

    /**
     * Get grade letter from percentage
     */
    public static Grade getGradeFromPercentage(int percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new InvalidGradeException("Percentage must be between 0 and 100");
        }
        return Grade.fromPercentage(percentage);
    }

    /**
     * Convert numeric grade to letter using switch expression
     */
    public static String convertNumericToLetter(int score) {
        return switch (score / 10) {  // Integer division
            case 10, 9 -> "A";
            case 8 -> "B";
            case 7 -> "C";
            case 6 -> "D";
            default -> "F";
        };
    }

    /**
     * Determine if grade meets requirement using pattern matching
     */
    public static boolean meetsRequirement(Object requirement, Grade grade) {
        return switch (requirement) {
            case null -> false;
            case Grade requiredGrade -> grade.getGradePoint() >= requiredGrade.getGradePoint();
            case Double minGPA -> grade.getGradePoint() >= minGPA;
            case Integer minPercentage -> 
                grade.getMinPercentage() >= minPercentage;
            case String desc when desc.equals("PASSING") -> grade.isPassing();
            default -> false;
        };
    }

    /**
     * Format grade with color coding (text-based)
     */
    public static String formatGradeWithStatus(Grade grade) {
        String status = switch (grade) {
            case A_PLUS, A, A_MINUS -> "[HONORS]";
            case B_PLUS, B, B_MINUS -> "[GOOD]";
            case C_PLUS, C, C_MINUS -> "[PASS]";
            case D -> "[CONDITIONAL]";
            case F -> "[FAIL]";
        };
        
        return String.format("%s %s (%.1f)", grade, status, grade.getGradePoint());
    }
}