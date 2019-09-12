package com.fsd.example.helper;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryHelper {
	private static SessionFactory sessionFactory ;

	public static void buildSessionFactory() {
		try {
			Configuration config = new Configuration();
			sessionFactory = config.configure().buildSessionFactory();
		} catch (Throwable e) {
			System.err.println("Error in creating SessionFactory object." + e.getMessage());
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
