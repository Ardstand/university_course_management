package com.university.util;

/**
 * Validation utility class demonstrating:
 * - Static utility methods
 * - String API usage
 * - Method overloading
 * 
 * OOP Features Demonstrated:
 * - Static methods
 * - String API
 * - Method overloading
 */
public class ValidationUtil {
    
    private ValidationUtil() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Validate email format
     * Demonstrates String API methods
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        
        // String API: contains(), indexOf(), lastIndexOf()
        return email.contains("@") && 
               email.indexOf("@") > 0 &&
               email.indexOf("@") == email.lastIndexOf("@") &&
               email.indexOf("@") < email.lastIndexOf(".");
    }

    /**
     * Validate phone number
     */
    public static boolean isValidPhone(String phone) {
        if (phone == null) {
            return false;
        }
        
        // String API: replaceAll(), matches()
        String cleaned = phone.replaceAll("[\\s\\-\\(\\)]", "");
        return cleaned.matches("\\d{10,15}");
    }

    /**
     * Validate student ID format
     * Method overloading - different parameters
     */
    public static boolean isValidStudentId(String id) {
        return id != null && id.matches("STU\\d{5}");
    }

    /**
     * Overloaded validation with custom pattern
     */
    public static boolean isValidStudentId(String id, String pattern) {
        return id != null && id.matches(pattern);
    }

    /**
     * Validate course code
     */
    public static boolean isValidCourseCode(String code) {
        if (code == null || code.isEmpty()) {
            return false;
        }
        
        // String API: length(), toUpperCase(), matches()
        return code.length() >= 5 && 
               code.length() <= 10 &&
               code.toUpperCase().matches("[A-Z]{2,4}\\d{3,4}");
    }

    /**
     * Sanitize string input
     * Demonstrates String API methods
     */
    public static String sanitize(String input) {
        if (input == null) {
            return "";
        }
        
        // String API: trim(), replaceAll()
        return input.trim().replaceAll("\\s+", " ");
    }

    /**
     * Check if string is null or empty
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty() || str.trim().isEmpty();
    }
}