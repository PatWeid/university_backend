package com.example.university_backend.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Staff {

    @Id
    @GeneratedValue
    private Long id;
    private Long staffID;
    private String firstName;
    private String lastName;
    private Date dob;
    private String gender;
    private String email;
    public Staff() {

    }
    public Staff(Long staffID, String firstName, String lastName, Date dob, String gender, String email) {
        this.staffID = staffID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStaffID() {
        return staffID;
    }

    public void setStaffID(Long staffID) {
        this.staffID = staffID;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
        Staff staff = (Staff) o;
        return id.equals(staff.id) && staffID.equals(staff.staffID) && firstName.equals(staff.firstName) && Objects.equals(lastName, staff.lastName) && Objects.equals(dob, staff.dob) && Objects.equals(gender, staff.gender) && email.equals(staff.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, staffID, firstName, lastName, dob, gender, email);
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", staffID=" + staffID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
