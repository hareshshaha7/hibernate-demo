package com.haresh.hibernatedemo.config;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.haresh.hibernatedemo.model.Student;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
				
				// Hibernate settings equivalent to Hibernate.cfg.xml
				Properties properties = new Properties();
				properties.put(Environment.DRIVER, "org.h2.Driver");
				properties.put(Environment.URL, "jdbc:h2:mem:test");
				properties.put(Environment.USER, "sa");
				properties.put(Environment.PASS, "");
				properties.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
				
				properties.put(Environment.SHOW_SQL, true);
				
				properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				
				properties.put(Environment.HBM2DDL_AUTO, "create-drop");
				
				configuration.setProperties(properties);
				
				configuration.addAnnotatedClass(Student.class);
				
				ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
				
				sessionFactory = configuration.buildSessionFactory(registry);
								
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return sessionFactory;
		
	}

}
