package com.laioffer.travel_planner_backend.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "days")
public class Day implements Serializable {

    private static final long serialVersionUID = 4980339460759086827L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dayId;

    @ManyToOne
    private Trip trip;

    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Stop> stops;

    @OneToMany
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
