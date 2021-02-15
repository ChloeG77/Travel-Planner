package com.laioffer.travel_planner_backend.dao;


import com.laioffer.travel_planner_backend.entity.Day;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DayDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void addDay(Day day) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(day);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void deleteDay (int dayId) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Day day = session.get(Day.class, dayId);
            session.delete(day);
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