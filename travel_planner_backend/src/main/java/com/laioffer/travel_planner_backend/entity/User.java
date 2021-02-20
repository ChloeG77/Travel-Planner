package com.laioffer.travel_planner_backend.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 2664312460856584164L;

    @Id
    private String emailId;

    private String password;

    private java.sql.Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Trip> allTrips;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Place> placesVisited;

    @ElementCollection
    private Set<Tag> tags;

    private boolean enabled;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
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

    public void setDateOfBirth(Date dataOfBirth) {
        this.dateOfBirth = dataOfBirth;
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

    public List<Place> getPlacesVisited() {
        return placesVisited;
    }

    public void setPlacesVisited(List<Place> placesVisited) {
        this.placesVisited = placesVisited;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
