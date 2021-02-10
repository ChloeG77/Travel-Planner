package com.laioffer.travel_planner_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "trips")
public class Trip implements Serializable {
		
		private static final long serialVersionUID = -4534543461915910352L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int tripId;
		
		@ManyToOne
		@JsonIgnore
		private User user;
		
		private java.sql.Date dateCreated;
		
		@OneToMany
		private Set<City> cities;
		
		@Enumerated(EnumType.STRING)
		private TripType type;
		
		@Temporal(TemporalType.DATE)
		private java.sql.Date startDate;
		
		private int numDays;
		
		@OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, fetch= FetchType.EAGER)
		private List<Day> days;
		
		@OneToMany
		private Set<Place> places;
		
		@Column(name = "trip_rating")
		private double rating;
		
		private boolean isPrivate;
		
		public int getTripId() {
				return tripId;
		}
		
		public void setTripId(int tripId) {
				this.tripId = tripId;
		}
		
		public User getUser() {
				return user;
		}
		
		public void setUser(User user) {
				this.user = user;
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
		
		public void setPlaces(Set<Place> places) {
				this.places = places;
		}
		
		public double getRating() {
				return rating;
		}
		
		public void setRating(double rating) {
				this.rating = rating;
		}
		
		public boolean isPrivate() {
				return isPrivate;
		}
		
		public void setPrivate(boolean aPrivate) {
				isPrivate = aPrivate;
		}
}
