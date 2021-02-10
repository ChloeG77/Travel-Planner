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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cities")
public class City implements Serializable {
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int cityId;
		
		private String state;
		
		private String country;
		
		@OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
		private Set<Place> places;
}
