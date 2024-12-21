package com.example.ecommerce.model.helper;

import com.example.ecommerce.model.Role;

import java.time.LocalDate;
import java.util.List;

public class UserDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String password;
    private String contact_no;

    private String email;
    private LocalDate dob;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private String status;
    private String profilePic;

    private List<Role> role;

    public UserDTO(int id, String firstName, String lastName, String password, String contact_no, String email, LocalDate dob, LocalDate createdDate, LocalDate updatedDate, String status, String profilePic, List<Role> role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.contact_no = contact_no;
        this.email = email;
        this.dob = dob;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.status = status;
        this.profilePic = profilePic;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }
}
