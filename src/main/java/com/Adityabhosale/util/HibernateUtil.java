package com.Adityabhosale.util;

import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static final SessionFactory factory;


    static {
        try {
           factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            System.out.println("✅✅✅ Hibernate Util SessionFactory Created");
        }catch (Exception e){
            System.err.println("❌❌❌ Hibernate Initilization Error!");
            throw new ExceptionInInitializerError(e);

        }
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }
   public  static void shutdown() {
        if (factory != null && !factory.isClosed()) {
            factory.close();
            System.out.println("✅✅✅ SessionFactory Closed");
        }

    }
}
