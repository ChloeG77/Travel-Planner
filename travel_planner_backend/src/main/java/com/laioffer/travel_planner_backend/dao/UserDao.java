package com.laioffer.travel_planner_backend.dao;

import com.laioffer.travel_planner_backend.entity.Authorities;
import com.laioffer.travel_planner_backend.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;


    public void addUser(User user) {
        Authorities authorities = new Authorities();
        authorities.setAuthorities("ROLE_USER");
        authorities.setEmailId(user.getEmailId());
        Session session = null;
        try {
            session = sessionFactory.unwrap(Session.class);
            session.beginTransaction();// PREVERNT dirty data
            session.save(authorities);
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace(); // if dirty data situation happen, rollback
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public User getCustomerByUserName(String userName) {
        User user = null;
        try (Session session = sessionFactory.unwrap(Session.class)) {

            Criteria criteria = session.createCriteria(User.class);
            user = (User) criteria.add(Restrictions.eq("emailId", userName)).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
