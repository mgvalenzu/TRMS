package com.revature.repositories;

import com.revature.models.Reimbursement;
import com.revature.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReimbursementRepoHBImpl implements ReimbursementRepo{
    @Override
    public Reimbursement addReimbursement(Reimbursement r) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {
//            session.beginTransaction();
            tx = session.beginTransaction();
            r.setId((int) session.save(r)); // alt way lets try if this is correct
//            r.session.persist(r);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if(tx != null) tx.rollback();
            return null;
        } finally {
            session.close();
        }
        return r;
    }

    @Override
    public List<Reimbursement> getAllReimbursements() {

        Session session = HibernateUtil.getSession();
        List<Reimbursement> reimbursements = null;

        try {
            reimbursements = session.createQuery("FROM Reimbursement").list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return reimbursements;
    }

    @Override
    public Reimbursement getReimbursement(int id) {

        Session session = HibernateUtil.getSession();
        Reimbursement r = null;

        try {
            r = session.get(Reimbursement.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return r;
    }

    @Override
    public Reimbursement updateReimbursement(Reimbursement change) {

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
    public Reimbursement deleteReimbursement(int id) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Reimbursement r = null;

        try {
            tx = session.beginTransaction();
            r = session.get(Reimbursement.class, id);
            session.delete(r);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if(tx != null) tx.rollback();
        } finally {
            session.close();
        }
        return r;
    }
}
