package com.laioffer.travel_planner_backend.dao;

@Repository
public class PlaceDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void addPlace(Place place) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(place);
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


    public void deletePlace(int placeId) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Place place = session.get(Place.class, placeId);
            session.delete(place);
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

    public void updatePlace(Place place) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(place);
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
