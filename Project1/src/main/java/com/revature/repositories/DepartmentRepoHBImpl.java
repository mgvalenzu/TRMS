package com.revature.repositories;

import com.revature.models.Department;
import com.revature.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DepartmentRepoHBImpl implements DepartmentRepo{


    @Override
    public Department addDepartment(Department d) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            session.persist(d);
            tx.commit();
        }catch(HibernateException e){
            e.printStackTrace();
            if(tx != null) tx.rollback();
            return null;
        }finally{
            session.close();
        }
        return d;
    }

    @Override
    public List<Department> getAllDepartments() {

        Session session = HibernateUtil.getSession();
        List<Department> departments = null;

        try{
            departments = session.createQuery("FROM Department").list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return departments;
    }

    @Override
    public Department getDepartment(int id) {

        Session session = HibernateUtil.getSession();
        Department d = null;

        try {
            d = session.get(Department.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return d;
    }


    @Override
    public Department getDepartment(String name) {

        Department d = null;

        try (Session session = HibernateUtil.getSession()){
            Criteria crit = session.createCriteria(Department.class);
            crit.add(Restrictions.eq("name", name));
            List<Department> departments = crit.list();
            if(departments.size() > 0) {
                d = (Department) departments.get(0);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return d;
    }

    @Override
    public Department updateDepartment(Department change) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(change);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
            return null;
        } finally {
            session.close();
        }
        return change;
    }

    @Override
    public Department deleteDepartment(int id) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Department d = null;

        try {
            tx = session.beginTransaction();
            d = session.get(Department.class, id);
            session.delete(d);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
            return null;
        } finally {
            session.close();
        }

        return d;
    }
}
