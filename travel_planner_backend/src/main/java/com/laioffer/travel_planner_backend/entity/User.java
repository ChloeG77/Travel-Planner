package com.laioffer.travel_planner_backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "users")
public class User implements Serializable {
    
    private static final long serialVersionUID = 2664312460856584164L;
    
    @Id
    private String email;
    
    @NaturalId
    private String username;
    
    @JsonIgnore
    private String password;

    private boolean enabled;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
        joinColumns = @JoinColumn(name = "email_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private List<Trip> allTrips;
    @ElementCollection
    private Set<Tag> tags;
    
    
    public User() {
    
    }
    
    public User(String email, String username, String password, Date date, Gender gender) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.dateOfBirth = date;
        this.gender = gender;
    }
    
    public String getUsername() {
        return username;
    }

//    @ManyToMany(cascade = CascadeType.PERSIST)
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private List<Place> placesVisited;
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String emailId) {
        this.email = emailId;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public Gender getGender() {
        return gender;
    }
    
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
    public List<Trip> getAllTrips() {
        return allTrips;
    }
    
    public void setAllTrips(List<Trip> allTrips) {
        this.allTrips = allTrips;
    }
    
    public Set<Tag> getTags() {
        return tags;
    }
    
    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
    
    public Set<Role> getRoles() {
        return roles;
    }
    
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    
    @Override
    public String toString() {
        return "User{" +
            "email='" + email + '\'' +
            ", username='" + username + '\'' +
            ", enabled=" + enabled +
            ", roles=" + roles +
            ", dateOfBirth=" + dateOfBirth +
            ", gender=" + gender +
            '}';
    }
    
    //    public List<Place> getPlacesVisited() {
//        return placesVisited;
//    }
//
//    public void setPlacesVisited(List<Place> placesVisited) {
//        this.placesVisited = placesVisited;
//    }
    public boolean isEnabled() {
        return enabled;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
}
