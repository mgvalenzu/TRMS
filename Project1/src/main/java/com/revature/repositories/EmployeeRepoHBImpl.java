package com.revature.repositories;

import com.revature.models.Department;
import com.revature.models.Employee;
import com.revature.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class EmployeeRepoHBImpl implements EmployeeRepo{
    @Override
    public Employee addEmployee(Employee e) {

        // Get session object and set up db transaction
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
    public List<Employee> getAllEmployees() {

        Session session = HibernateUtil.getSession();
        List<Employee> employees = null;

        try {
            employees = session.createQuery("From Employee").list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employees;
    }

    @Override
    public Employee getEmployee(int id) {

        Session session = HibernateUtil.getSession();
        Employee e = null;

        try {
            e = session.get(Employee.class, id);
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return e;
    }

//    @Override
//    public Employee getEmployee(String username, String password) {
//        Employee e = null;
//        try (Session session = HibernateUtil.getSession()) {
//            Criteria crit = session.createCriteria(Employee.class);
//            crit.add(Restrictions.eq("username", username));
//            crit.add(Restrictions.eq("password", password));
//
//            List<Employee> employees = crit.list();
//
//            if(employees.size() > 0) {
//                e = (Employee)employees.get(0);
//            }
//        } catch (HibernateException ex) {
//            ex.printStackTrace();
//        }
//        return e;
//    }

    @Override
    public Employee updateEmployee(Employee change) {

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
    public Employee deleteEmployee(int id) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Employee employee = null;

        try {
            tx = session.beginTransaction();
            employee = session.get(Employee.class, id); // grab the emp object we are trying to destroy
            session.delete(employee); // delete from current DB
            tx.commit(); // finalize the transaction - make it final to the DB
        } catch (HibernateException e) {
            e.printStackTrace();
            if(tx != null) tx.rollback();
            return null;
        } finally {
            session.close();
        }
        return employee;
    }
}
