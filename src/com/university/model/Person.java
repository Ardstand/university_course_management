package com.university.model;

import java.time.LocalDate;

/**
 * Sealed class demonstrating:
 * - Sealed classes (Java 17+) - restricts which classes can extend this class
 * - Constructor chaining with this()
 * - Use of this. to reference instance variables
 * - Encapsulation with private fields
 * - Method overloading (multiple constructors)
 * 
 * OOP Features Demonstrated:
 * - Sealed classes
 * - this() vs this.
 * - Encapsulation
 * - Constructor overloading
 * - Inheritance foundation
 */
public sealed class Person permits Student, Instructor {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;

    /**
     * Constructor demonstrating this() - constructor chaining
     * Calls the main constructor with null values
     */
    public Person(String firstName, String lastName) {
        this(firstName, lastName, null, null, null);  // this() calls another constructor
    }

    /**
     * Constructor with more parameters
     * Demonstrates this() constructor chaining
     */
    public Person(String firstName, String lastName, String email) {
        this(firstName, lastName, email, null, null);  // this() chains to main constructor
    }

    /**
     * Main constructor demonstrating this. to reference instance variables
     * this. distinguishes instance variables from parameters
     */
    public Person(String firstName, String lastName, String email, 
                  String phone, LocalDate dateOfBirth) {
        this.firstName = firstName;      // this. refers to instance variable
        this.lastName = lastName;        // this. refers to instance variable
        this.email = email;              // this. refers to instance variable
        this.phone = phone;              // this. refers to instance variable
        this.dateOfBirth = dateOfBirth;  // this. refers to instance variable
    }

    // Encapsulation - getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;  // this. distinguishes parameter from field
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Method to get full name - demonstrates String API
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Method to be overridden by subclasses
     * Demonstrates polymorphism foundation
     */
    public String getRole() {
        return "Person";
    }

    /**
     * Calculate age - demonstrates Date API usage
     */
    public int getAge() {
        if (dateOfBirth == null) {
            return 0;
        }
        return LocalDate.now().getYear() - dateOfBirth.getYear();
    }

    @Override
    public String toString() {
        return String.format("Person{name='%s %s', email='%s'}", 
            firstName, lastName, email);
    }
}