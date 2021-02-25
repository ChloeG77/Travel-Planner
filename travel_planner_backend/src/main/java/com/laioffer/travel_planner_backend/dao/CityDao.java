package com.laioffer.travel_planner_backend.dao;

import com.laioffer.travel_planner_backend.entity.City;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CityDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public City getCityById(long cityId) {
        City city = null;
        try (Session session = sessionFactory.openSession()) {
            city = session.get(City.class, cityId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return city;
    }
    
    public City getCity(String cityName, String state, String country) {
        City city = null;
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(City.class);
            city = (City) criteria.add(Restrictions.eq("name", cityName))
                .add(Restrictions.eq("state", state))
                .add(Restrictions.eq("country", country))
                .uniqueResult();
            if (city == null) {
                city = new City();
                city.setCountry(country);
                city.setState(state);
                city.setName(cityName);
                session.save(city);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return city;
    }
}