package com.laioffer.travel_planner_backend.message.response;

import com.laioffer.travel_planner_backend.entity.Stop;

import java.util.List;

public class RouteResponse {

    private List<Stop> route;

    public RouteResponse(List<Stop> route) {
        this.route = route;
    }

    public List<Stop> getRoute() {
        return route;
    }

    public void setRoute(List<Stop> route) {
        this.route = route;
    }


}
