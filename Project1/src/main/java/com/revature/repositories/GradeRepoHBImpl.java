package com.revature.repositories;

import com.revature.models.Grade;
import com.revature.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GradeRepoHBImpl implements GradeRepo{
    @Override
    public Grade addGrade(Grade g) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.persist(g);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if(tx != null) tx.rollback();
            return null;
        } finally {
            session.close();
        }
        return g;
    }

    @Override
    public List<Grade> getAllGrades() {

        Session session = HibernateUtil.getSession();
        List<Grade> grades = null;

        try {
            grades = session.createQuery("FROM Grade").list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return grades;
    }

    @Override
    public Grade getGrade(int id) {

        Session session = HibernateUtil.getSession();
        Grade g = null;

        try {
            g = session.get(Grade.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return g;
    }

    @Override
    public Grade updateGrade(Grade change) {

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
    public Grade deleteGrade(int id) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Grade g = null;

        try {
            tx = session.beginTransaction();
            g = session.get(Grade.class, id);
            session.delete(g);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if(tx != null) tx.rollback();
            return null;
        } finally {
            session.close();
        }
        return g;
    }
}
