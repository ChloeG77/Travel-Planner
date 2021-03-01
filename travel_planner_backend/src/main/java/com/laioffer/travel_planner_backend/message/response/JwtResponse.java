package com.laioffer.travel_planner_backend.message.response;

import com.laioffer.travel_planner_backend.entity.Trip;
import java.util.List;

public class JwtResponse {
    
    private String token;
    private String type = "Bearer";
    private List<Trip> trips;
    
    public JwtResponse(String accessToken, List<Trip> trips) {
        this.token = accessToken;
        this.trips = trips;
    }
    
    public List<Trip> getTrips() {
        return trips;
    }
    
    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
    
    public String getAccessToken() {
        return token;
    }
    
    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }
    
    public String getTokenType() {
        return type;
    }
    
    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }
}