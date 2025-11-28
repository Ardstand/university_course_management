package com.university.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Custom Immutable Type demonstrating:
 * - Immutability best practices
 * - Final class (cannot be extended)
 * - Final fields (cannot be changed)
 * - Defensive copying in constructor and getters
 * - No setters (immutable after construction)
 * 
 * OOP Features Demonstrated:
 * - Custom immutable type
 * - Defensive copying
 * - Final keyword usage
 * - Encapsulation
 */
public final class Transcript {  // final - cannot be extended
    private final String studentId;           // final - cannot be changed
    private final String studentName;         // final - cannot be changed
    private final DepartmentType major;       // final - cannot be changed
    private final List<Enrollment> enrollments;  // final reference (list contents can change internally)
    private final double gpa;                 // final - cannot be changed
    private final LocalDate generatedDate;    // final - cannot be changed
    private final int totalCredits;           // final - cannot be changed

    /**
     * Constructor with defensive copying
     * Takes mutable objects but makes defensive copies
     */
    public Transcript(String studentId, String studentName, DepartmentType major, 
                     List<Enrollment> enrollments, double gpa, int totalCredits) {
        // Validate inputs
        if (studentId == null || studentId.isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty");
        }
        if (studentName == null || studentName.isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty");
        }
        if (major == null) {
            throw new IllegalArgumentException("Major cannot be null");
        }
        
        // Assign final fields (can only be done once)
        this.studentId = studentId;
        this.studentName = studentName;
        this.major = major;
        
        // Defensive copy - create new list from provided list
        // This prevents external modification of the internal list
        this.enrollments = enrollments != null ? 
            new ArrayList<>(enrollments) : new ArrayList<>();
        
        this.gpa = gpa;
        this.totalCredits = totalCredits;
        this.generatedDate = LocalDate.now();  // Set at creation time
    }

    /**
     * Factory method to create transcript from student
     */
    public static Transcript createFromStudent(Student student, List<Enrollment> enrollments) {
        return new Transcript(
            student.getStudentId(),
            student.getFullName(),
            student.getMajor(),
            enrollments,
            student.getGPA(),
            calculateTotalCredits(enrollments)
        );
    }

    /**
     * Helper method to calculate total credits
     */
    private static int calculateTotalCredits(List<Enrollment> enrollments) {
        // This is a simplified version - would need course credit info in real system
        return enrollments != null ? enrollments.size() * 3 : 0;
    }

    /**
     * Getter with defensive copy
     * Returns unmodifiable view to prevent external modification
     */
    public List<Enrollment> getEnrollments() {
        // Return unmodifiable view - even safer than copying
        return Collections.unmodifiableList(enrollments);
    }

    /**
     * Generate transcript as formatted string
     * Demonstrates StringBuilder usage
     */
    public String generateTranscriptReport() {
        var report = new StringBuilder();  // LVTI
        
        report.append("=" .repeat(50)).append("\n");
        report.append("OFFICIAL TRANSCRIPT\n");
        report.append("=".repeat(50)).append("\n\n");
        
        report.append("Student ID: ").append(studentId).append("\n");
        report.append("Name: ").append(studentName).append("\n");
        report.append("Major: ").append(major.getFullName()).append("\n");
        report.append("GPA: ").append(String.format("%.2f", gpa)).append("\n");
        report.append("Total Credits: ").append(totalCredits).append("\n");
        report.append("Generated: ").append(generatedDate).append("\n\n");
        
        report.append("COURSE HISTORY:\n");
        report.append("-".repeat(50)).append("\n");
        
        for (var enrollment : enrollments) {  // LVTI
            report.append(String.format("%-10s  %-30s  %s\n",
                enrollment.courseCode(),
                enrollment.enrollmentDate(),
                enrollment.finalGrade() != null ? enrollment.finalGrade() : "In Progress"
            ));
        }
        
        report.append("=".repeat(50)).append("\n");
        
        return report.toString();
    }

    // Getters only - no setters (immutable)
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public DepartmentType getMajor() {
        return major;
    }

    public double getGpa() {
        return gpa;
    }

    public LocalDate getGeneratedDate() {
        return generatedDate;  // LocalDate is immutable, so safe to return directly
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    /**
     * Immutable objects should override equals and hashCode
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Transcript that = (Transcript) obj;
        return studentId.equals(that.studentId) && 
               generatedDate.equals(that.generatedDate);
    }

    @Override
    public int hashCode() {
        return studentId.hashCode() * 31 + generatedDate.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Transcript{student='%s', gpa=%.2f, credits=%d, date=%s}", 
            studentName, gpa, totalCredits, generatedDate);
    }
}