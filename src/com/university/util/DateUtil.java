package com.university.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * Date utility class demonstrating Date API (java.time)
 * 
 * OOP Features Demonstrated:
 * - Java Date API (LocalDate, LocalDateTime, Period)
 * - DateTimeFormatter
 * - Static utility methods
 */
public class DateUtil {
    
    // DateTimeFormatter patterns
    private static final DateTimeFormatter DATE_FORMATTER = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATETIME_FORMATTER = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DISPLAY_FORMATTER = 
        DateTimeFormatter.ofPattern("MMMM dd, yyyy");

    private DateUtil() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Get current date
     */
    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    /**
     * Get current date and time
     */
    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    /**
     * Format date for display
     */
    public static String formatDate(LocalDate date) {
        return date != null ? date.format(DISPLAY_FORMATTER) : "";
    }

    /**
     * Format date time for display
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(DATETIME_FORMATTER) : "";
    }

    /**
     * Parse date from string
     */
    public static LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString, DATE_FORMATTER);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Calculate age from date of birth
     * Demonstrates Period class from Date API
     */
    public static int calculateAge(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            return 0;
        }
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    /**
     * Check if date is in the past
     */
    public static boolean isInPast(LocalDate date) {
        return date != null && date.isBefore(LocalDate.now());
    }

    /**
     * Check if date is in the future
     */
    public static boolean isInFuture(LocalDate date) {
        return date != null && date.isAfter(LocalDate.now());
    }

    /**
     * Get days between two dates
     */
    public static long daysBetween(LocalDate start, LocalDate end) {
        if (start == null || end == null) {
            return 0;
        }
        return Period.between(start, end).getDays();
    }

    /**
     * Get semester from date
     */
    public static String getSemester(LocalDate date) {
        int month = date.getMonthValue();
        int year = date.getYear();
        
        return switch (month) {
            case 1, 2, 3, 4, 5 -> "Spring " + year;
            case 6, 7, 8 -> "Summer " + year;
            case 9, 10, 11, 12 -> "Fall " + year;
            default -> "Unknown";
        };
    }

    /**
     * Check if student is eligible by age
     */
    public static boolean isEligibleAge(LocalDate dateOfBirth, int minimumAge) {
        return calculateAge(dateOfBirth) >= minimumAge;
    }
}