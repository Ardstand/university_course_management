package com.university.service;

import com.university.model.Student;
import com.university.model.DepartmentType;
import com.university.model.Grade;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Student service class demonstrating:
 * - Lambdas
 * - Predicates
 * - Method references
 * - Effectively final variables
 * - Defensive copying
 * 
 * OOP Features Demonstrated:
 * - Lambdas (Predicate)
 * - Method references
 * - Final/effectively final
 * - Streams API
 */
public class StudentService {
    
    private final List<Student> students;  // final - reference cannot change

    public StudentService() {
        this.students = new ArrayList<>();
    }

    /**
     * Add student to service
     */
    public void addStudent(Student student) {
        if (student != null) {
            students.add(student);
        }
    }

    /**
     * Get all students with defensive copying
     */
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);  // Defensive copy
    }

    /**
     * Filter students using Predicate (lambda)
     * Demonstrates lambdas and Predicate functional interface
     */
    public List<Student> filterStudents(Predicate<Student> predicate) {
        List<Student> result = new ArrayList<>();
        
        for (Student student : students) {
            if (predicate.test(student)) {  // Using Predicate
                result.add(student);
            }
        }
        
        return result;
    }

    /**
     * Find students by major using lambda
     * Demonstrates lambda expression
     */
    public List<Student> findByMajor(DepartmentType major) {
        // Lambda expression: (parameter) -> expression
        return filterStudents(student -> student.getMajor() == major);
    }

    /**
     * Find students with GPA above threshold using lambda
     */
    public List<Student> findByMinGPA(double minGPA) {
        // Lambda with condition
        return filterStudents(student -> student.getGPA() >= minGPA);
    }

    /**
     * Find honor roll students using lambda
     */
    public List<Student> findHonorRollStudents() {
        // Lambda using interface method
        return filterStudents(student -> student.isHonorRoll());
    }

    /**
     * Find students by multiple criteria using lambda composition
     * Demonstrates combining Predicates
     */
    public List<Student> findByMajorAndGPA(DepartmentType major, double minGPA) {
        // Predicate composition using and()
        Predicate<Student> majorPredicate = s -> s.getMajor() == major;
        Predicate<Student> gpaPredicate = s -> s.getGPA() >= minGPA;
        
        // Combining predicates
        return filterStudents(majorPredicate.and(gpaPredicate));
    }

    /**
     * Find active students using method reference
     * Demonstrates method reference
     */
    public List<Student> findActiveStudents() {
        // Method reference: ClassName::methodName
        return filterStudents(Student::isActive);
    }

    /**
     * Count students matching predicate
     */
    public long countStudents(Predicate<Student> predicate) {
        return students.stream()
            .filter(predicate)  // Predicate in stream
            .count();
    }

    /**
     * Demonstrates effectively final variable in lambda
     */
    public List<Student> findStudentsInDepartments(DepartmentType... departments) {
        // departments parameter is effectively final (not modified after initialization)
        return filterStudents(student -> {
            // Using effectively final variable inside lambda
            for (DepartmentType dept : departments) {
                if (student.getMajor() == dept) {
                    return true;
                }
            }
            return false;
        });
    }

    /**
     * Complex lambda with multiple statements
     */
    public List<Student> findTopPerformers(int topN) {
        final double threshold = 3.5;  // final variable used in lambda
        
        return students.stream()
            .filter(s -> s.getGPA() >= threshold)  // Using final variable
            .sorted((s1, s2) -> Double.compare(s2.getGPA(), s1.getGPA()))  // Lambda comparator
            .limit(topN)
            .collect(Collectors.toList());
    }

    /**
     * Predicate factory methods
     */
    public static Predicate<Student> hasMinGPA(double minGPA) {
        return student -> student.getGPA() >= minGPA;
    }

    public static Predicate<Student> inDepartment(DepartmentType dept) {
        return student -> student.getMajor() == dept;
    }

    public static Predicate<Student> isActive() {
        return Student::isActive;  // Method reference
    }

    /**
     * Using predefined predicates with method references
     */
    public List<String> getStudentNames() {
        return students.stream()
            .map(Student::getFullName)  // Method reference
            .collect(Collectors.toList());
    }

    /**
     * Get students using complex lambda
     */
    public List<Student> getStudentsByComplexCriteria(
            DepartmentType dept, 
            double minGPA, 
            boolean activeOnly) {
        
        // Building predicate with lambda
        Predicate<Student> criteria = student -> {
            boolean matchesDept = student.getMajor() == dept;
            boolean matchesGPA = student.getGPA() >= minGPA;
            boolean matchesActive = !activeOnly || student.isActive();
            
            return matchesDept && matchesGPA && matchesActive;
        };
        
        return filterStudents(criteria);
    }

    /**
     * Demonstrate Predicate.not() (Java 11+)
     */
    public List<Student> findInactiveStudents() {
        return filterStudents(Predicate.not(Student::isActive));
    }

    /**
     * Get count of students by criteria
     */
    public long getStudentCount() {
        return students.size();
    }

    /**
     * Calculate average GPA using streams and method reference
     */
    public double calculateAverageGPA() {
        return students.stream()
            .mapToDouble(Student::getGPA)  // Method reference
            .average()
            .orElse(0.0);
    }
}