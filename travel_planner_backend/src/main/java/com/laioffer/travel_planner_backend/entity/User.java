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
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "users")
public class User implements Serializable {
		
		private static final long serialVersionUID = 2664312460856584164L;
		
		@Id
		private String emailId;
		
		private String password;
		
		private java.sql.Date dataOfBirth;
		
		@Enumerated(EnumType.STRING)
		private Gender gender;
		
		@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch= FetchType.EAGER)
		private List<Trip> allTrips;
		
//		@ManyToMany(cascade = CascadeType.PERSIST)
//		@LazyCollection(LazyCollectionOption.FALSE)
//		private List<Place> placesVisited;
		
		@ElementCollection
		private Set<Tag> tags;
		
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
		
		public Date getDataOfBirth() {
				return dataOfBirth;
		}
		
		public void setDataOfBirth(Date dataOfBirth) {
				this.dataOfBirth = dataOfBirth;
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
}
