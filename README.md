# University Course Management System - OOP1 Assignment

## Project Overview

A comprehensive University Course Management System demonstrating Java 21 LTS features and advanced object-oriented programming concepts.

## Application Domain: University Course Management

This application manages a university's academic operations including:

- **Student Management**: Enrollment, grades, academic records
- **Course Management**: Course creation, scheduling, prerequisites
- **Instructor Management**: Teaching assignments, office hours
- **Department Operations**: Course offerings, faculty management
- **Grade Processing**: Grade calculation, transcripts, GPA
- **Enrollment System**: Course registration, waitlists, capacity management

## Why This Domain?

- **Relatable**: Students understand university operations
- **Complex Relationships**: Students, courses, instructors, departments
- **Rich Business Logic**: Grade calculations, prerequisites, enrollment rules
- **Real-world Scenarios**: Demonstrates practical OOP design

## Complete Java Features Demonstrated

### Fundamentals ✅

| Feature                   | Location                        | Description                                     |
| ------------------------- | ------------------------------- | ----------------------------------------------- |
| Classes                   | All model classes               | Person, Student, Instructor, Course, Department |
| `this()` vs `this.`       | Person.java, Course.java        | Constructor chaining vs instance reference      |
| Method Overloading        | Student.java, Course.java       | Multiple constructors and methods               |
| Varargs                   | Department.java, GradeUtil.java | Variable argument methods                       |
| LVTI                      | Main.java, Services             | `var` keyword usage                             |
| Encapsulation             | All model classes               | Private fields with getters/setters             |
| Interfaces                | Enrollable.java, Gradeable.java | Multiple interfaces                             |
| Inheritance               | Person → Student/Instructor     | Class hierarchy                                 |
| Overriding & Polymorphism | Student/Instructor classes      | Method overriding                               |
| `super()` vs `super.`     | Student.java, Instructor.java   | Parent constructor vs parent method             |
| Checked Exceptions        | EnrollmentException.java        | Custom checked exceptions                       |
| Unchecked Exceptions      | InvalidGradeException.java      | Custom runtime exceptions                       |
| Enums                     | Grade.java, Department.java     | Enumeration types                               |
| Arrays                    | Course.java                     | Student arrays, grade arrays                    |
| String API                | StringUtil.java                 | String manipulation                             |
| StringBuilder             | Report generation               | Efficient string building                       |
| List/ArrayList            | All service classes             | Collection usage                                |
| Date API                  | Course.java, Student.java       | LocalDate, LocalDateTime                        |

### Advanced Features ✅

| Feature                   | Location                   | Description                  |
| ------------------------- | -------------------------- | ---------------------------- |
| Call-by-value             | EnrollmentService.java     | Defensive copying examples   |
| Defensive Copying         | Student.java, Course.java  | Immutable collections        |
| Private Interface Methods | Gradeable.java             | Helper methods in interfaces |
| Default Interface Methods | Enrollable.java            | Default implementations      |
| Static Interface Methods  | Gradeable.java             | Utility methods              |
| Records                   | CourseSchedule, Enrollment | Immutable data carriers      |
| Custom Immutable Type     | Transcript.java            | Fully immutable class        |
| Lambdas (Predicate)       | StudentService.java        | Filtering with predicates    |
| Final/Effectively Final   | Lambda examples            | Closure demonstrations       |
| Method References         | Service classes            | Method reference usage       |
| Switch Expressions        | GradeUtil.java             | Modern switch syntax         |
| Pattern Matching          | Student.java               | instanceof patterns          |
| Sealed Classes            | Person sealed class        | Restricted inheritance       |

## Project Structure

```
university_course_management/
├── README.md
├── REPORT.md                          # Comprehensive technical report
├── src/
│   └── com/university/
│       ├── Main.java                   # Application entry point
│       ├── model/
│       │   ├── Person.java             # Sealed abstract base class
│       │   ├── Student.java            # Final student class
│       │   ├── Instructor.java         # Final instructor class
│       │   ├── Course.java             # Course entity
│       │   ├── DepartmentType.java     # Department enum
│       │   ├── Grade.java              # Grade enum with values
│       │   ├── CourseSchedule.java     # Record for scheduling
│       │   ├── Enrollment.java         # Record for enrollment
│       │   ├── Transcript.java         # Immutable transcript
│       │   ├── Enrollable.java         # Interface with default methods
│       │   └── Gradeable.java          # Interface with static methods
│       ├── service/
│       │   ├── StudentService.java     # Student operations
│       │   ├── CourseService.java      # Course management
│       │   ├── EnrollmentService.java  # Enrollment processing
│       │   └── GradeService.java       # Grade calculations
│       ├── util/
│       │   ├── DateUtil.java           # Date utilities
│       │   ├── GradeUtil.java          # Grade utilities with switch
│       │   └── ValidationUtil.java     # Input validation
│       └── exception/
│           ├── EnrollmentException.java    # Checked exception
│           ├── InvalidGradeException.java  # Unchecked exception
│           └── CourseFullException.java    # Checked exception
└── docs/
    └── screenshots/                    # Application screenshots
```

## Compilation and Execution

### Prerequisites

- Java 21 LTS or higher
- No external dependencies (uses only Java Core API)

### Compile

```bash
cd university_course_management
javac -d bin src/com/university/**/*.java src/com/university/*.java
```

### Run

```bash
java -cp bin com.university.Main
```

### For Java 22/23 Features (if using preview features)

```bash
javac --enable-preview --release 22 -d bin src/com/university/**/*.java
java --enable-preview -cp bin com.university.Main
```

## Key Design Decisions

### 1. Sealed Class Hierarchy

```
Person (sealed)
├── Student (final)
└── Instructor (final)
```

This prevents unauthorized extensions while allowing controlled polymorphism.

### 2. Records for Data Transfer

Using records for `CourseSchedule` and `Enrollment` provides immutable, concise data carriers.

### 3. Defensive Copying

All collections returned from getters are copied to prevent external modification.

### 4. Exception Hierarchy

- `EnrollmentException` (checked) - for enrollment failures
- `CourseFullException` (checked) - when course capacity reached
- `InvalidGradeException` (unchecked) - for invalid grade values

## Sample Output

The application demonstrates:

- Creating students and instructors
- Enrolling students in courses
- Recording and calculating grades
- Generating transcripts
- Filtering students using predicates
- Pattern matching for type checking
- Switch expressions for grade conversion

## Testing Approach

The Main.java file includes comprehensive demonstrations of:

- All OOP features
- Error handling scenarios
- Edge cases
- Polymorphic behavior

## Author Information

**Course**: Object-Oriented Programming 1
**Institution**: Technological University of the Shannon (TUS)
**Submission Date**: November 29, 2024
**Java Version**: Java 21 LTS (with Java 22 preview features)

## Additional Notes

- All code is thoroughly commented
- Follows Java naming conventions
- Demonstrates industry best practices
- No external libraries used (pure Java Core API)
- Git repository included with consistent commits
