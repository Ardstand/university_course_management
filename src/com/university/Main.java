package com.university;

import com.university.model.*;
import com.university.service.*;
import com.university.util.*;
import com.university.exception.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * Interactive University Course Management System
 * Demonstrates all OOP features with user interaction
 * 
 * @author TUS Student
 * @version 2.0 - Interactive
 * @since 2024-11-29
 */
public class Main {
    
    private static Scanner scanner = new Scanner(System.in);
    private static StudentService studentService = new StudentService();
    private static EnrollmentService enrollmentService = new EnrollmentService();
    private static List<Course> courses = new ArrayList<>();
    private static List<Instructor> instructors = new ArrayList<>();
    
    public static void main(String[] args) {
        System.out.println("=".repeat(70));
        System.out.println("    UNIVERSITY COURSE MANAGEMENT SYSTEM");
        System.out.println("    OOP1 Assignment - Java 21 LTS Interactive Version");
        System.out.println("=".repeat(70));
        System.out.println();
        
        // Initialize with some sample data
        initializeSampleData();
        
        boolean running = true;
        while (running) {
            running = showMainMenu();
        }
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("Thank you for using the University Management System!");
        System.out.println("=".repeat(70));
        scanner.close();
    }

    private static void initializeSampleData() {
        Instructor inst1 = new Instructor("Dr. Sarah", "Johnson", "s.johnson@tus.ie",
            DepartmentType.COMPUTER_SCIENCE, 75000);
        inst1.setOfficeHours("Monday 2-4pm", "Wednesday 3-5pm");
        instructors.add(inst1);
        
        Instructor inst2 = new Instructor("Prof. Michael", "Chen", "m.chen@tus.ie",
            DepartmentType.MATHEMATICS, 80000);
        inst2.setOfficeHours("Tuesday 1-3pm", "Thursday 2-4pm");
        instructors.add(inst2);
        
        Course cs101 = new Course("CS101", "Introduction to Programming", 
            DepartmentType.COMPUTER_SCIENCE, 3, 30);
        cs101.setInstructor(inst1);
        cs101.setSchedule(new CourseSchedule("Monday", LocalTime.of(9, 0), 
            LocalTime.of(10, 30), "Room 101"));
        courses.add(cs101);
        
        Course cs201 = new Course("CS201", "Data Structures", 
            DepartmentType.COMPUTER_SCIENCE, 4, 25);
        cs201.setInstructor(inst1);
        cs201.setPrerequisites("CS101");
        cs201.setSchedule(new CourseSchedule("Wednesday", LocalTime.of(11, 0), 
            LocalTime.of(12, 30), "Room 102"));
        courses.add(cs201);
        
        Course math101 = new Course("MATH101", "Calculus I", 
            DepartmentType.MATHEMATICS, 4, 35);
        math101.setInstructor(inst2);
        math101.setSchedule(new CourseSchedule("Tuesday", LocalTime.of(10, 0), 
            LocalTime.of(11, 30), "Room 201"));
        courses.add(math101);
        
        System.out.println("✓ System initialized with " + courses.size() + " sample courses");
        System.out.println();
    }

    private static boolean showMainMenu() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("MAIN MENU");
        System.out.println("=".repeat(70));
        System.out.println("1.  Add Student");
        System.out.println("2.  View All Students");
        System.out.println("3.  Search Student");
        System.out.println("4.  Enroll Student in Course");
        System.out.println("5.  Assign Grade");
        System.out.println("6.  View Student Transcript");
        System.out.println("7.  View All Courses");
        System.out.println("8.  View Reports");
        System.out.println("9.  OOP Features Demonstration");
        System.out.println("0.  Exit");
        System.out.println("=".repeat(70));
        System.out.print("Enter your choice: ");
        
        try {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) return true;
            
            int choice = Integer.parseInt(input);
            System.out.println();
            
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewAllStudents();
                case 3 -> searchStudent();
                case 4 -> enrollStudent();
                case 5 -> assignGrade();
                case 6 -> viewTranscript();
                case 7 -> viewAllCourses();
                case 8 -> viewReports();
                case 9 -> demonstrateOOP();
                case 0 -> { return false; }
                default -> System.out.println("Invalid choice! Please enter 0-9.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number.");
        }
        
        return true;
    }

    private static void addStudent() {
        System.out.println("--- Add New Student ---");
        
        System.out.print("First name: ");
        String firstName = scanner.nextLine().trim();
        
        System.out.print("Last name: ");
        String lastName = scanner.nextLine().trim();
        
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        
        if (!ValidationUtil.isValidEmail(email)) {
            System.out.println("❌ Invalid email format!");
            return;
        }
        
        System.out.println("\nSelect Major:");
        DepartmentType[] depts = DepartmentType.values();
        for (int i = 0; i < depts.length; i++) {
            System.out.println((i + 1) + ". " + depts[i].getFullName());
        }
        System.out.print("Choice (1-" + depts.length + "): ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice < 1 || choice > depts.length) {
                System.out.println("❌ Invalid choice!");
                return;
            }
            
            Student student = new Student(firstName, lastName, email, depts[choice - 1]);
            studentService.addStudent(student);
            
            System.out.println("\n✓ Student added!");
            System.out.println("ID: " + student.getStudentId());
            System.out.println("Name: " + student.getFullName());
            System.out.println("Major: " + student.getMajor().getFullName());
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid input!");
        }
    }

    private static void viewAllStudents() {
        List<Student> students = studentService.getAllStudents();
        
        System.out.println("=".repeat(70));
        System.out.println("ALL STUDENTS (" + students.size() + ")");
        System.out.println("=".repeat(70));
        
        if (students.isEmpty()) {
            System.out.println("No students yet. Add one using option 1!");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    private static void searchStudent() {
        System.out.println("--- Search Student ---");
        System.out.print("Enter name (first or last): ");
        String name = scanner.nextLine().trim().toLowerCase();
        
        List<Student> found = new ArrayList<>();
        for (Student s : studentService.getAllStudents()) {
            if (s.getFirstName().toLowerCase().contains(name) ||
                s.getLastName().toLowerCase().contains(name)) {
                found.add(s);
            }
        }
        
        if (found.isEmpty()) {
            System.out.println("❌ No students found");
        } else {
            System.out.println("\n✓ Found " + found.size() + " student(s):");
            for (Student s : found) {
                System.out.println("  " + s);
            }
        }
    }

    private static void enrollStudent() {
        System.out.println("--- Enroll Student in Course ---");
        System.out.print("Student ID: ");
        String studentId = scanner.nextLine().trim();
        
        Student student = findStudent(studentId);
        if (student == null) {
            System.out.println("❌ Student not found!");
            return;
        }
        
        System.out.print("Course Code: ");
        String courseCode = scanner.nextLine().trim().toUpperCase();
        
        Course course = findCourse(courseCode);
        if (course == null) {
            System.out.println("❌ Course not found!");
            return;
        }
        
        try {
            enrollmentService.enrollStudent(student, course);
            System.out.println("✓ Enrolled " + student.getFullName() + " in " + course.getCourseName());
        } catch (CourseFullException e) {
            System.out.println("❌ " + e.getMessage());
        } catch (EnrollmentException e) {
            System.out.println("❌ Enrollment failed: " + e.getMessage());
        }
    }

    private static void assignGrade() {
        System.out.println("--- Assign Grade ---");
        System.out.print("Student ID: ");
        String studentId = scanner.nextLine().trim();
        
        Student student = findStudent(studentId);
        if (student == null) {
            System.out.println("❌ Student not found!");
            return;
        }
        
        System.out.print("Course Code: ");
        String courseCode = scanner.nextLine().trim().toUpperCase();
        
        System.out.println("\nGrades:");
        Grade[] grades = Grade.values();
        for (int i = 0; i < grades.length; i++) {
            System.out.println((i + 1) + ". " + grades[i] + " (" + grades[i].getGradePoint() + ")");
        }
        System.out.print("Choice: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice < 1 || choice > grades.length) {
                System.out.println("❌ Invalid grade!");
                return;
            }
            
            Grade grade = grades[choice - 1];
            enrollmentService.assignGrade(studentId, courseCode, grade);
            student.addGrade(grade);
            
            System.out.println("✓ Grade " + grade + " assigned");
            System.out.println("New GPA: " + String.format("%.2f", student.getGPA()));
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid input!");
        } catch (EnrollmentException e) {
            System.out.println("❌ Failed: " + e.getMessage());
        }
    }

    private static void viewTranscript() {
        System.out.println("--- View Transcript ---");
        System.out.print("Student ID: ");
        String studentId = scanner.nextLine().trim();
        
        Student student = findStudent(studentId);
        if (student == null) {
            System.out.println("❌ Student not found!");
            return;
        }
        
        List<Enrollment> enrollments = enrollmentService.getStudentEnrollments(studentId);
        Transcript transcript = Transcript.createFromStudent(student, enrollments);
        System.out.println("\n" + transcript.generateTranscriptReport());
    }

    private static void viewAllCourses() {
        System.out.println("=".repeat(70));
        System.out.println("ALL COURSES (" + courses.size() + ")");
        System.out.println("=".repeat(70));
        
        for (Course c : courses) {
            System.out.println(c);
            if (c.getInstructor() != null) {
                System.out.println("  Instructor: " + c.getInstructor().getFullName());
            }
        }
    }

    private static void viewReports() {
        System.out.println("--- Reports ---");
        System.out.println("1. Honor Roll Students (GPA >= 3.5)");
        System.out.println("2. Students by Major");
        System.out.println("3. All Instructors");
        System.out.print("Choice: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            System.out.println();
            
            switch (choice) {
                case 1 -> {
                    List<Student> honor = studentService.findHonorRollStudents();
                    System.out.println("Honor Roll (" + honor.size() + " students):");
                    for (Student s : honor) {
                        System.out.println("  " + s.getFullName() + " - GPA: " + 
                            String.format("%.2f", s.getGPA()));
                    }
                }
                case 2 -> {
                    for (DepartmentType dept : DepartmentType.values()) {
                        List<Student> students = studentService.findByMajor(dept);
                        System.out.println(dept.getFullName() + ": " + students.size() + " students");
                    }
                }
                case 3 -> {
                    System.out.println("Instructors (" + instructors.size() + "):");
                    for (Instructor i : instructors) {
                        System.out.println("  " + i);
                    }
                }
                default -> System.out.println("Invalid choice!");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid input!");
        }
    }

    private static void demonstrateOOP() {
        System.out.println("=".repeat(70));
        System.out.println("OOP FEATURES DEMONSTRATION");
        System.out.println("=".repeat(70));
        System.out.println("\nDemonstrating all required OOP features...\n");
        
        // Classes & Inheritance
        System.out.println("1. Classes, Inheritance, Polymorphism:");
        Student s1 = new Student("Demo", "Student", "demo@tus.ie", DepartmentType.COMPUTER_SCIENCE);
        Instructor i1 = new Instructor("Demo", "Prof", "prof@tus.ie", DepartmentType.MATHEMATICS, 70000);
        Person[] people = {s1, i1};
        for (Person p : people) {
            System.out.println("   " + p.getFullName() + " - Role: " + p.getRole());
        }
        
        // Enums
        System.out.println("\n2. Enums:");
        System.out.println("   Grade.A = " + Grade.A.getGradePoint() + " points");
        System.out.println("   DepartmentType.COMPUTER_SCIENCE = " + DepartmentType.COMPUTER_SCIENCE.getCode());
        
        // Method Overloading & Varargs
        System.out.println("\n3. Method Overloading & Varargs:");
        s1.addGrade(Grade.A);
        s1.addGrade(Grade.A_MINUS, Grade.B_PLUS);
        System.out.println("   Added grades: " + s1.getGrades());
        
        // LVTI
        System.out.println("\n4. LVTI (var keyword):");
        var name = s1.getFullName();
        var gpa = s1.getGPA();
        System.out.println("   var name = \"" + name + "\"");
        System.out.println("   var gpa = " + gpa);
        
        // Interfaces
        System.out.println("\n5. Interfaces:");
        System.out.println("   Student implements Enrollable and Gradeable");
        System.out.println("   Academic Standing: " + s1.getAcademicStanding());
        
        // Lambdas
        System.out.println("\n6. Lambdas & Predicates:");
        Predicate<Student> highGPA = student -> student.getGPA() >= 3.5;
        System.out.println("   Using lambda: student -> student.getGPA() >= 3.5");
        
        // Records
        System.out.println("\n7. Records:");
        CourseSchedule sched = new CourseSchedule("Monday", LocalTime.of(9, 0), 
            LocalTime.of(10, 30), "Room 101");
        System.out.println("   " + sched.getFormattedSchedule());
        
        // Switch Expressions
        System.out.println("\n8. Switch Expressions:");
        String desc = GradeUtil.getGradeDescription(Grade.A);
        System.out.println("   Grade.A description: " + desc);
        
        // Sealed Classes
        System.out.println("\n9. Sealed Classes:");
        System.out.println("   Person is sealed, permits only Student and Instructor");
        
        // Defensive Copying
        System.out.println("\n10. Defensive Copying:");
        List<Grade> grades = s1.getGrades();
        System.out.println("    Original: " + grades);
        grades.add(Grade.F);
        System.out.println("    After modifying copy: " + s1.getGrades() + " (unchanged)");
        
        System.out.println("\n✓ All OOP features demonstrated!");
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    private static Student findStudent(String id) {
        return studentService.getAllStudents().stream()
            .filter(s -> s.getStudentId().equals(id))
            .findFirst()
            .orElse(null);
    }

    private static Course findCourse(String code) {
        return courses.stream()
            .filter(c -> c.getCourseCode().equalsIgnoreCase(code))
            .findFirst()
            .orElse(null);
    }
}