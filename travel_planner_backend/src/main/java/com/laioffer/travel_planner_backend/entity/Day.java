package com.laioffer.travel_planner_backend.entity;

import java.io.Serializable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
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

@Entity
@Table(name = "days")
public class Day implements Serializable {
		
		private static final long serialVersionUID = 4980339460759086827L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int dayId;
		
		@ManyToOne
		private Trip trip;
		
		@OneToMany
		private Set<Stop> stops;
		
		@OneToMany
		private List<Stop> route;
		
		public int getDayId() {
				return dayId;
		}
		
		public void setDayId(int dayId) {
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
