package com.laioffer.travel_planner_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "trips")
public class Trip implements Serializable {

    private static final long serialVersionUID = -4534543461915910352L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tripId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    @Column(name = "trip_name")
    private String name;

    private Date dateCreated;

    @OneToMany
    private Set<City> cities = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private TripType type;

    private Date startDate;

    private int numDays;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Day> days = new ArrayList<>();

    @ManyToMany
    private Set<Place> places = new HashSet<>();

    @Column(name = "trip_rating")
    private double rating;

    private String review;

    private boolean isPrivate;

    public long getTripId() {
        return tripId;
    }

    public void setTripId(long tripId) {
        this.tripId = tripId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    public TripType getType() {
        return type;
    }

    public void setType(TripType type) {
        this.type = type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getNumDays() {
        return numDays;
    }

    public void setNumDays(int numDays) {
        this.numDays = numDays;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public Set<Place> getPlaces() {
        return places;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "tripId=" + tripId +
                ", name='" + name + '\'' +
                ", numDays=" + numDays +
                ", isPrivate=" + isPrivate +
                '}';
    }

    public void setPlaces(Set<Place> places) {
        this.places = places;
    }

    public void addPlace(Place place) {
        this.places.add(place);
    }

    public void deletePlace(Place place) {
        this.places.remove(place);
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }
}
