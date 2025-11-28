package com.university.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Instructor class demonstrating:
 * - Inheritance from sealed Person class
 * - Final class (cannot be extended further)
 * - super() and super. usage
 * - Method overriding
 * - Arrays
 * 
 * OOP Features Demonstrated:
 * - Inheritance
 * - Final class
 * - Overriding
 * - Arrays
 * - Defensive copying
 */
public final class Instructor extends Person {
    private String instructorId;
    private DepartmentType department;
    private String[] officeHours;  // Array demonstration
    private List<String> coursesTaught;
    private double salary;
    private static int instructorCounter = 0;

    /**
     * Constructor using super()
     */
    public Instructor(String firstName, String lastName, String email, 
                     DepartmentType department, double salary) {
        super(firstName, lastName, email);  // super() calls parent constructor
        this.instructorId = generateInstructorId();
        this.department = department;
        this.salary = salary;
        this.officeHours = new String[0];  // Initialize empty array
        this.coursesTaught = new ArrayList<>();
    }

    /**
     * Full constructor
     */
    public Instructor(String firstName, String lastName, String email, 
                     String phone, LocalDate dateOfBirth,
                     DepartmentType department, double salary) {
        super(firstName, lastName, email, phone, dateOfBirth);
        this.instructorId = generateInstructorId();
        this.department = department;
        this.salary = salary;
        this.officeHours = new String[0];
        this.coursesTaught = new ArrayList<>();
    }

    /**
     * Override parent method
     */
    @Override
    public String getRole() {
        return "Instructor - " + department.getFullName();
    }

    /**
     * Method using super. to access parent method
     */
    public String getInstructorProfile() {
        return super.getFullName() + " - " + department.getCode() + " Department";
    }

    /**
     * Set office hours using array
     * Demonstrates working with arrays
     */
    public void setOfficeHours(String... hours) {  // Varargs converted to array
        this.officeHours = hours;
    }

    /**
     * Get office hours with defensive copying
     * Demonstrates defensive copying for arrays
     */
    public String[] getOfficeHours() {
        // Defensive copy - clone the array
        return officeHours.clone();
    }

    /**
     * Add a course to teaching list
     */
    public void addCourse(String courseCode) {
        if (!coursesTaught.contains(courseCode)) {
            coursesTaught.add(courseCode);
        }
    }

    /**
     * Get courses with defensive copying
     */
    public List<String> getCoursesTaught() {
        return new ArrayList<>(coursesTaught);
    }

    private static String generateInstructorId() {
        return "INS" + String.format("%05d", ++instructorCounter);
    }

    // Getters and setters
    public String getInstructorId() {
        return instructorId;
    }

    public DepartmentType getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentType department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("Instructor{id='%s', name='%s', department=%s}", 
            instructorId, getFullName(), department);
    }
}