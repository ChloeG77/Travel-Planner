package com.laioffer.travel_planner_backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "days")
public class Day implements Serializable {
    
    private static final long serialVersionUID = 4980339460759086827L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dayId;
    
    @ManyToOne
    @JoinColumn(name = "tripId")
    @JsonManagedReference
    private Trip trip;
    
    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonBackReference
    private Set<Stop> stops;
    
    @OneToMany
    @JsonIgnoreProperties({"day", "place"})
    private List<Stop> route;
    
    public long getDayId() {
        return dayId;
    }
    
    public void setDayId(long dayId) {
        this.dayId = dayId;
    }
    
    public Trip getTrip() {
        return trip;
    }
    
    public void setTrip(Trip trip) {
        this.trip = trip;
    }
    
    public Set<Stop> getStops() {
        return stops;
    }
    
    public void setStops(Set<Stop> stops) {
        this.stops = stops;
    }
    
    public List<Stop> getRoute() {
        return route;
    }
    
    public void setRoute(List<Stop> route) {
        this.route = route;
    }
    
}
