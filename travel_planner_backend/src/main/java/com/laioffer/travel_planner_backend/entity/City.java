package com.laioffer.travel_planner_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cities")
public class City implements Serializable {
    
    private static final long serialVersionUID = 7443059535372816619L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cityId;
    
    @Column(name = "city_name")
    private String name;
    
    private String state;
    
    private String country;
    
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Place> places;
    
    @Override
    public String toString() {
        return name + ", " + state + ", " + country;
    }
    
    public long getCityId() {
        return cityId;
    }
    
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public Set<Place> getPlaces() {
        return places;
    }
    
    public void setPlaces(Set<Place> places) {
        this.places = places;
    }
}
