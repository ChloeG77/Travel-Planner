package com.laioffer.travel_planner_backend.entity;

import java.io.Serializable;


import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stops")
public class Stop implements Serializable {
		
		private static final long serialVersionUID = 2108856196456620160L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private String stopId;
		
		@ManyToOne
		private Place place;
		
		@ManyToOne
		private Day day;
		
		@Embedded
		private StopType type;
		
		public String getStopId() {
				return stopId;
		}
		
		public void setStopId(String stopId) {
				this.stopId = stopId;
		}
		
		public Place getPlace() {
				return place;
		}
		
		public void setPlace(Place place) {
				this.place = place;
		}
		
		public Day getDay() {
				return day;
		}
		
		public void setDay(Day day) {
				this.day = day;
		}
}
