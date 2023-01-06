package com.ba.boost.util;

import com.ba.boost.entity.Book;
import com.ba.boost.entity.BookDetail;
import com.ba.boost.entity.Student;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (HibernateUtil.sessionFactory == null) {
			HibernateUtil.sessionFactory = createSessionFactory();
		}
		return sessionFactory;
	}

	private static SessionFactory createSessionFactory() {
		Configuration config = new Configuration();
         /*
          * We should add out entitis here, with addAnnotatedClass method.
          */
		config.addAnnotatedClass(Book.class);
		config.addAnnotatedClass(BookDetail.class);
		config.addAnnotatedClass(Student.class);

		SessionFactory factory = config.configure("hibernate.cfg.xml").buildSessionFactory();
		System.out.println("Connection Successful...");
		return factory;

	}
}
