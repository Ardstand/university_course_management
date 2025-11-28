package com.university.model;

import java.time.LocalTime;

/**
 * Record demonstrating Records feature (Java 16+)
 * Records are immutable data carriers
 * Automatically provides:
 * - Constructor
 * - Getters (accessor methods)
 * - equals()
 * - hashCode()
 * - toString()
 * 
 * OOP Features Demonstrated:
 * - Records
 * - Immutability
 * - Compact constructors
 */
public record CourseSchedule(
    String dayOfWeek,
    LocalTime startTime,
    LocalTime endTime,
    String room
) {
    
    /**
     * Compact constructor for validation
     * Only available in records
     */
    public CourseSchedule {
        if (dayOfWeek == null || dayOfWeek.isEmpty()) {
            throw new IllegalArgumentException("Day of week cannot be null or empty");
        }
        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("Times cannot be null");
        }
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time");
        }
        if (room == null || room.isEmpty()) {
            throw new IllegalArgumentException("Room cannot be null or empty");
        }
    }
    
    /**
     * Custom method in record
     */
    public int getDurationMinutes() {
        return (int) java.time.Duration.between(startTime, endTime).toMinutes();
    }
    
    /**
     * Format schedule as readable string
     */
    public String getFormattedSchedule() {
        return String.format("%s %s-%s in %s", 
            dayOfWeek, startTime, endTime, room);
    }
}