package com.laioffer.travel_planner_backend.message.request;

import com.laioffer.travel_planner_backend.entity.Gender;

public class SignUpForm {
    
    private String email;
    
    private String username;

//    private Set<String> role;
    
    private String password;
    
    private String dateOfBirth;
    private Gender gender;
    
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public Gender getGender() {
        return gender;
    }
    
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

//    public Set<String> getRole() {
//    	return this.role;
//    }
//
//    public void setRole(Set<String> role) {
//    	this.role = role;
//    }
    
    @Override
    public String toString() {
        return "SignUpForm{" +
            "email='" + email + '\'' +
            ", username='" + username + '\'' +
            ", dateOfBirth='" + dateOfBirth + '\'' +
            ", gender=" + gender +
            '}';
    }
}