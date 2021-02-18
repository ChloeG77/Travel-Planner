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
		
		public Day getDayById(int dayId) {
				Day day = null;
				try (Session session = sessionFactory.openSession()) {
						day = session.get(Day.class, dayId);
				} catch (Exception e) {
						e.printStackTrace();
				}
				return day;
		}
		
		public void update(Day day) {
				try (Session session = sessionFactory.openSession()) {
						session.beginTransaction();
						session.saveOrUpdate(day);
						session.getTransaction().commit();
				} catch (Exception e) {
						e.printStackTrace();
				}
		}
}

