package com.laioffer.travel_planner_backend.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "authorities")
public class Authorities implements Serializable {
		
		private static final long serialVersionUID = 4218915207877699263L;
		
		@Id
		private String emailId;
		
		private String authorities;
		
		public String getEmailId() {
				return emailId;
		}
		
		public void setEmailId(String emailId) {
				this.emailId = emailId;
		}
		
		public String getAuthorities() {
				return authorities;
		}
		
		public void setAuthorities(String authorities) {
				this.authorities = authorities;
		}
}
