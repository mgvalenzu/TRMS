package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    //Abstract away creating our session factory and the process of creating sessions from that Session Factory

    private static SessionFactory sf = new Configuration().configure().buildSessionFactory();

    public static Session getSession() {
        return sf.openSession();
    }
}
