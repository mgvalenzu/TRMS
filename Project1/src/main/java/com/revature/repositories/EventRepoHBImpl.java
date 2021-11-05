package com.revature.repositories;

import com.revature.models.Event;
import com.revature.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EventRepoHBImpl implements EventRepo{
    @Override
    public Event addEvent(Event e) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.persist(e);
            tx.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            if(tx != null) tx.rollback();
            return null;
        } finally {
            session.close();
        }
        return e;
    }

    @Override
    public List<Event> getAllEvents() {

        Session session = HibernateUtil.getSession();
        List<Event> events = null;

        try {
            events = session.createQuery("FROM Event").list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return events;
    }

    @Override
    public Event getEvent(int id) {

        Session session = HibernateUtil.getSession();
        Event event = null;

        try {
            event = session.get(Event.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return event;
    }

    @Override
    public Event updateEvent(Event change) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(change);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if(tx != null) tx.rollback();
            return null;
        } finally {
            session.close();
        }
        return change;
    }

    @Override
    public Event deleteEvent(int id) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Event event = null;

        try {
            tx = session.beginTransaction();
            event = session.get(Event.class, id);
            session.delete(event);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if(tx != null) tx.rollback();
            return null;
        } finally {
            session.close();
        }
        return event;
    }
}
