package com.university.model;

/**
 * Grade enum demonstrating enums with associated values and methods.
 * Represents letter grades with corresponding grade points and percentages.
 * 
 * OOP Features Demonstrated:
 * - Enums with multiple fields
 * - Enum methods
 * - Static enum methods
 */
public enum Grade {
    A_PLUS(4.0, 95, 100),
    A(4.0, 90, 94),
    A_MINUS(3.7, 85, 89),
    B_PLUS(3.3, 80, 84),
    B(3.0, 75, 79),
    B_MINUS(2.7, 70, 74),
    C_PLUS(2.3, 65, 69),
    C(2.0, 60, 64),
    C_MINUS(1.7, 55, 59),
    D(1.0, 50, 54),
    F(0.0, 0, 49);

    private final double gradePoint;
    private final int minPercentage;
    private final int maxPercentage;

    Grade(double gradePoint, int minPercentage, int maxPercentage) {
        this.gradePoint = gradePoint;
        this.minPercentage = minPercentage;
        this.maxPercentage = maxPercentage;
    }

    public double getGradePoint() {
        return gradePoint;
    }

    public int getMinPercentage() {
        return minPercentage;
    }

    public int getMaxPercentage() {
        return maxPercentage;
    }

    /**
     * Static method to get grade from percentage
     * Demonstrates static methods in enums
     */
    public static Grade fromPercentage(int percentage) {
        for (Grade grade : Grade.values()) {
            if (percentage >= grade.minPercentage && percentage <= grade.maxPercentage) {
                return grade;
            }
        }
        throw new IllegalArgumentException("Invalid percentage: " + percentage);
    }

    public boolean isPassing() {
        return this != F;
    }

    @Override
    public String toString() {
        return name().replace("_", "-");
    }
}