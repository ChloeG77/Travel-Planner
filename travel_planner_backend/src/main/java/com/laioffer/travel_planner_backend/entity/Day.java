package com.laioffer.travel_planner_backend.entity;

import java.io.Serializable;

import java.sql.Date;
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
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int dayId;
		
		@ManyToOne
		private Trip trip;
		
		@OneToMany
		private Set<Place> places;
		
		@OneToMany
		private List<Place> route;
		
		

}
