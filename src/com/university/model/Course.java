package com.university.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Course class demonstrating:
 * - Encapsulation
 * - Arrays
 * - ArrayList
 * - Method overloading
 * - LVTI (Local Variable Type Inference)
 * - StringBuilder
 * 
 * OOP Features Demonstrated:
 * - Encapsulation
 * - Arrays
 * - Lists
 * - Method overloading
 * - LVTI
 * - StringBuilder usage
 */
public class Course {
    private String courseCode;
    private String courseName;
    private DepartmentType department;
    private int credits;
    private int capacity;
    private int enrolled;
    private Instructor instructor;
    private CourseSchedule schedule;
    private String[] prerequisites;  // Array of prerequisite course codes
    private List<Student> enrolledStudents;

    /**
     * Constructor demonstrating this. and initialization
     */
    public Course(String courseCode, String courseName, DepartmentType department, int credits) {
        this(courseCode, courseName, department, credits, 30);  // this() constructor chaining
    }

    /**
     * Main constructor with capacity
     */
    public Course(String courseCode, String courseName, DepartmentType department, 
                  int credits, int capacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.department = department;
        this.credits = credits;
        this.capacity = capacity;
        this.enrolled = 0;
        this.prerequisites = new String[0];
        this.enrolledStudents = new ArrayList<>();
    }

    /**
     * Method demonstrating LVTI (var keyword)
     * Local Variable Type Inference - Java 10+
     */
    public String getCourseInfo() {
        // var - type is inferred as StringBuilder
        var info = new StringBuilder();  // LVTI
        
        info.append("Course: ").append(courseCode).append("\n");
        info.append("Name: ").append(courseName).append("\n");
        info.append("Department: ").append(department.getFullName()).append("\n");
        info.append("Credits: ").append(credits).append("\n");
        info.append("Enrollment: ").append(enrolled).append("/").append(capacity).append("\n");
        
        if (instructor != null) {
            // var inferred as String
            var instructorName = instructor.getFullName();
            info.append("Instructor: ").append(instructorName).append("\n");
        }
        
        if (prerequisites.length > 0) {
            info.append("Prerequisites: ");
            // var inferred as String in loop
            for (var prereq : prerequisites) {
                info.append(prereq).append(" ");
            }
            info.append("\n");
        }
        
        return info.toString();
    }

    /**
     * Method overloading - enroll student
     */
    public boolean enrollStudent(Student student) throws Exception {
        if (enrolled >= capacity) {
            throw new Exception("Course is full");
        }
        
        if (enrolledStudents.contains(student)) {
            return false;  // Already enrolled
        }
        
        enrolledStudents.add(student);
        enrolled++;
        return true;
    }

    /**
     * Overloaded method - enroll multiple students using varargs
     */
    public int enrollStudent(Student... students) {  // Varargs
        int count = 0;
        for (Student student : students) {
            try {
                if (enrollStudent(student)) {
                    count++;
                }
            } catch (Exception e) {
                break;  // Stop if course is full
            }
        }
        return count;
    }

    /**
     * Remove student from course
     */
    public boolean removeStudent(Student student) {
        if (enrolledStudents.remove(student)) {
            enrolled--;
            return true;
        }
        return false;
    }

    /**
     * Set prerequisites using varargs (converted to array)
     */
    public void setPrerequisites(String... prereqs) {  // Varargs
        this.prerequisites = prereqs;
    }

    /**
     * Get prerequisites with defensive copying
     */
    public String[] getPrerequisites() {
        return prerequisites.clone();  // Defensive copy
    }

    /**
     * Get enrolled students with defensive copying
     */
    public List<Student> getEnrolledStudents() {
        return new ArrayList<>(enrolledStudents);  // Defensive copy
    }

    /**
     * Check if course is full
     */
    public boolean isFull() {
        return enrolled >= capacity;
    }

    /**
     * Get available seats
     */
    public int getAvailableSeats() {
        return capacity - enrolled;
    }

    // Getters and setters
    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public DepartmentType getDepartment() {
        return department;
    }

    public int getCredits() {
        return credits;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getEnrolled() {
        return enrolled;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public CourseSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(CourseSchedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return String.format("Course{code='%s', name='%s', enrolled=%d/%d}", 
            courseCode, courseName, enrolled, capacity);
    }
}