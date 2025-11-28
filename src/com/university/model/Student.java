package com.university.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Student class demonstrating:
 * - Inheritance (extends Person)
 * - Part of sealed class hierarchy (final prevents further extension)
 * - super() vs super. usage
 * - Method overriding
 * - Polymorphism
 * - Implementing multiple interfaces
 * - Method overloading
 * - Varargs
 * - Defensive copying
 * 
 * OOP Features Demonstrated:
 * - Inheritance
 * - super() vs super.
 * - Overriding
 * - Polymorphism
 * - Interfaces implementation
 * - Method overloading
 * - Varargs
 * - Defensive copying
 * - ArrayList usage
 */
public final class Student extends Person implements Enrollable, Gradeable {
    private String studentId;
    private DepartmentType major;
    private List<Grade> grades;
    private LocalDate enrollmentDate;
    private double gpa;
    private boolean active;
    private static int studentCounter = 0;

    /**
     * Constructor demonstrating super() - calling parent constructor
     * super() must be the first statement in constructor
     */
    public Student(String firstName, String lastName, String email, DepartmentType major) {
        super(firstName, lastName, email);  // super() calls parent constructor
        this.studentId = generateStudentId();
        this.major = major;
        this.grades = new ArrayList<>();
        this.enrollmentDate = LocalDate.now();
        this.gpa = 0.0;
        this.active = true;
    }

    /**
     * Overloaded constructor with more parameters
     */
    public Student(String firstName, String lastName, String email, 
                   String phone, LocalDate dateOfBirth, DepartmentType major) {
        super(firstName, lastName, email, phone, dateOfBirth);  // super() with all params
        this.studentId = generateStudentId();
        this.major = major;
        this.grades = new ArrayList<>();
        this.enrollmentDate = LocalDate.now();
        this.gpa = 0.0;
        this.active = true;
    }

    /**
     * Method demonstrating super. to access parent class method
     * super. accesses parent class members
     */
    public String getDetailedInfo() {
        // super. calls parent class method
        String parentInfo = super.toString();
        return String.format("%s, Student ID: %s, Major: %s, GPA: %.2f", 
            parentInfo, studentId, major, gpa);
    }

    /**
     * Method using super. to access parent method
     */
    public String getFullStudentName() {
        return "Student: " + super.getFullName();  // super. calls parent method
    }

    /**
     * Override parent method - demonstrating polymorphism and overriding
     * @Override annotation ensures we're actually overriding
     */
    @Override
    public String getRole() {
        return "Student";
    }

    /**
     * Method overloading - same method name, different parameters
     * Adds a single grade
     */
    public void addGrade(Grade grade) {
        if (grade == null) {
            throw new IllegalArgumentException("Grade cannot be null");
        }
        grades.add(grade);
        calculateGPA();
    }

    /**
     * Overloaded method - adds multiple grades using varargs
     * Demonstrates varargs (variable arguments)
     */
    public void addGrade(Grade... newGrades) {  // Varargs - takes 0 or more Grade objects
        for (Grade grade : newGrades) {
            if (grade != null) {
                grades.add(grade);
            }
        }
        calculateGPA();
    }

    /**
     * Overloaded method - adds grades from a list
     */
    public void addGrade(List<Grade> newGrades) {
        if (newGrades != null) {
            grades.addAll(newGrades);
            calculateGPA();
        }
    }

    /**
     * Add loyalty points (placeholder for demonstration)
     */
    public void addLoyaltyPoints(double points) {
        // This is a placeholder method for call-by-value demonstration
        // In a real system, this would track student loyalty/reward points
    }

    /**
     * Calculates GPA from grades
     */
    private void calculateGPA() {
        if (grades.isEmpty()) {
            this.gpa = 0.0;
            return;
        }
        
        double totalPoints = 0.0;
        for (Grade grade : grades) {
            totalPoints += grade.getGradePoint();
        }
        
        this.gpa = Math.round((totalPoints / grades.size()) * 100.0) / 100.0;
    }

    /**
     * Generate unique student ID
     */
    private static String generateStudentId() {
        return "STU" + String.format("%05d", ++studentCounter);
    }

    // Implementing Enrollable interface
    @Override
    public String getEnrollmentId() {
        return studentId;
    }

    @Override
    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    // Implementing Gradeable interface
    @Override
    public double getGPA() {
        return gpa;
    }

    @Override
    public List<Grade> getGrades() {
        // Defensive copying - return a copy to prevent external modification
        // Demonstrates call-by-value and defensive copying
        return new ArrayList<>(grades);
    }

    // Getters and setters
    public String getStudentId() {
        return studentId;
    }

    public DepartmentType getMajor() {
        return major;
    }

    public void setMajor(DepartmentType major) {
        this.major = major;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return String.format("Student{id='%s', name='%s', major=%s, gpa=%.2f}", 
            studentId, getFullName(), major, gpa);
    }
}