package com.university.model;

/**
 * Enum demonstrating enumeration types in Java.
 * Represents different academic departments in the university.
 * 
 * OOP Features Demonstrated:
 * - Enums with fields and methods
 * - Encapsulation within enums
 */
public enum DepartmentType {
    COMPUTER_SCIENCE("Computer Science", "CS"),
    MATHEMATICS("Mathematics", "MATH"),
    ENGINEERING("Engineering", "ENG"),
    BUSINESS("Business Administration", "BUS"),
    ARTS("Arts and Humanities", "ARTS");

    private final String fullName;
    private final String code;

    // Constructor for enum constants
    DepartmentType(String fullName, String code) {
        this.fullName = fullName;
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCode() {
        return code;
    }

    // Method demonstrating enum behavior
    public String getDescription() {
        return String.format("%s (%s)", fullName, code);
    }

    @Override
    public String toString() {
        return fullName;
    }
}