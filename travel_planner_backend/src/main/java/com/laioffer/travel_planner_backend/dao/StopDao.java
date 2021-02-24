package com.laioffer.travel_planner_backend.dao;

import com.laioffer.travel_planner_backend.entity.Day;
import com.laioffer.travel_planner_backend.entity.Stop;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StopDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public Stop getStopById(long stopId) {
        Stop stop = null;
        try (Session session = sessionFactory.openSession()) {
            stop = session.get(Stop.class, stopId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stop;
    }
    
    public void update(Stop stop) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(stop);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteStop(Stop stop) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            
            Day day = stop.getDay();
            Set<Stop> places = day.getStops();
            List<Stop> route = day.getRoute();
            places.remove(stop);
            route.remove(stop);
            
            session.beginTransaction();
            session.delete(stop);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
