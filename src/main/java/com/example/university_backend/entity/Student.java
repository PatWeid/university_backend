package com.example.university_backend.entity;


import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Students")
public class Student {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private Date dob;
    private Date joiningDate;
    private String gender;
    private String department;
    private String email;

    public Student() {

    }

    public Student(Long id, String firstName, String lastName, Date dob, Date joiningDate, String gender, String department, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.joiningDate = joiningDate;
        this.gender = gender;
        this.department = department;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id.equals(student.id) && firstName.equals(student.firstName) && Objects.equals(lastName, student.lastName) && dob.equals(student.dob) && joiningDate.equals(student.joiningDate) && Objects.equals(gender, student.gender) && department.equals(student.department) && email.equals(student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dob, joiningDate, gender, department, email);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", joiningDate=" + joiningDate +
                ", gender='" + gender + '\'' +
                ", department='" + department + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
