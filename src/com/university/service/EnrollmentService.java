package com.university.service;

import com.university.model.Student;
import com.university.model.Course;
import com.university.model.Enrollment;
import com.university.model.Grade;
import com.university.exception.EnrollmentException;
import com.university.exception.CourseFullException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Enrollment service demonstrating:
 * - Call-by-value behavior
 * - Defensive copying
 * - Checked exceptions
 * - Method overloading
 * 
 * OOP Features Demonstrated:
 * - Call-by-value
 * - Defensive copying
 * - Exception handling
 */
public class EnrollmentService {
    
    private final List<Enrollment> enrollments;

    public EnrollmentService() {
        this.enrollments = new ArrayList<>();
    }

    /**
     * Demonstrates call-by-value:
     * Java passes references by value, not the objects themselves.
     * Changes to the object affect the original, but reassigning the reference doesn't.
     */
    public void demonstrateCallByValue(Student student) {
        // This modifies the actual student object (reference passed by value)
        student.addLoyaltyPoints(10.0);  // AFFECTS original
        
        // This only changes the local reference, not the original reference
        student = new Student("New", "Student", "new@email.com", 
            student.getMajor());  // DOES NOT affect original
        
        // The original student object was modified but the caller's reference unchanged
    }

    /**
     * Enroll student in course with exception handling
     * Demonstrates checked exceptions
     */
    public Enrollment enrollStudent(Student student, Course course) 
            throws EnrollmentException, CourseFullException {
        
        // Validation
        if (student == null) {
            throw new EnrollmentException("Student cannot be null");
        }
        if (course == null) {
            throw new EnrollmentException("Course cannot be null");
        }
        if (!student.isActive()) {
            throw new EnrollmentException("Student is not active");
        }

        // Check if course is full
        if (course.isFull()) {
            throw new CourseFullException(course.getCourseCode(), course.getCapacity());
        }

        // Check if already enrolled
        if (isStudentEnrolled(student.getStudentId(), course.getCourseCode())) {
            throw new EnrollmentException("Student already enrolled in this course");
        }

        try {
            // Enroll in course
            course.enrollStudent(student);
            
            // Create enrollment record
            Enrollment enrollment = new Enrollment(
                student.getStudentId(),
                course.getCourseCode(),
                LocalDate.now()
            );
            
            enrollments.add(enrollment);
            return enrollment;
            
        } catch (Exception e) {
            throw new EnrollmentException("Failed to enroll student", e);
        }
    }

    /**
     * Method overloading - batch enrollment
     */
    public List<Enrollment> enrollStudent(Student student, Course... courses) 
            throws EnrollmentException {
        
        List<Enrollment> result = new ArrayList<>();
        
        for (Course course : courses) {
            try {
                Enrollment enrollment = enrollStudent(student, course);
                result.add(enrollment);
            } catch (CourseFullException e) {
                // Log but continue with other courses
                System.err.println("Skipping full course: " + e.getMessage());
            }
        }
        
        return result;
    }

    /**
     * Assign grade to enrollment
     */
    public void assignGrade(String studentId, String courseCode, Grade grade) 
            throws EnrollmentException {
        
        if (grade == null) {
            throw new EnrollmentException("Grade cannot be null");
        }

        // Find enrollment
        Enrollment oldEnrollment = findEnrollment(studentId, courseCode);
        if (oldEnrollment == null) {
            throw new EnrollmentException("Enrollment not found");
        }

        // Create new enrollment with grade (records are immutable)
        Enrollment newEnrollment = new Enrollment(
            studentId,
            courseCode,
            oldEnrollment.enrollmentDate(),
            grade
        );

        // Replace old enrollment
        enrollments.remove(oldEnrollment);
        enrollments.add(newEnrollment);
    }

    /**
     * Get student enrollments with defensive copying
     * Demonstrates defensive copying to prevent external modification
     */
    public List<Enrollment> getStudentEnrollments(String studentId) {
        List<Enrollment> studentEnrollments = new ArrayList<>();
        
        for (Enrollment enrollment : enrollments) {
            if (enrollment.studentId().equals(studentId)) {
                studentEnrollments.add(enrollment);
            }
        }
        
        // Return defensive copy
        return new ArrayList<>(studentEnrollments);
    }

    /**
     * Get course enrollments with defensive copying
     */
    public List<Enrollment> getCourseEnrollments(String courseCode) {
        List<Enrollment> courseEnrollments = new ArrayList<>();
        
        for (Enrollment enrollment : enrollments) {
            if (enrollment.courseCode().equals(courseCode)) {
                courseEnrollments.add(enrollment);
            }
        }
        
        // Defensive copy
        return new ArrayList<>(courseEnrollments);
    }

    /**
     * Get all enrollments with defensive copying
     */
    public List<Enrollment> getAllEnrollments() {
        // Return defensive copy to prevent external modification
        return new ArrayList<>(enrollments);
    }

    /**
     * Check if student is enrolled in course
     */
    private boolean isStudentEnrolled(String studentId, String courseCode) {
        return enrollments.stream()
            .anyMatch(e -> e.studentId().equals(studentId) && 
                          e.courseCode().equals(courseCode));
    }

    /**
     * Find specific enrollment
     */
    private Enrollment findEnrollment(String studentId, String courseCode) {
        return enrollments.stream()
            .filter(e -> e.studentId().equals(studentId) && 
                        e.courseCode().equals(courseCode))
            .findFirst()
            .orElse(null);
    }

    /**
     * Drop course
     */
    public boolean dropCourse(String studentId, String courseCode, Course course) {
        Enrollment enrollment = findEnrollment(studentId, courseCode);
        
        if (enrollment != null && !enrollment.isGraded()) {
            enrollments.remove(enrollment);
            // Note: This demonstrates that we need the actual course object
            // to modify its enrollment count - call by value means we need
            // the reference to modify the object's state
            return true;
        }
        
        return false;
    }

    /**
     * Get enrollment count
     */
    public int getEnrollmentCount() {
        return enrollments.size();
    }
}