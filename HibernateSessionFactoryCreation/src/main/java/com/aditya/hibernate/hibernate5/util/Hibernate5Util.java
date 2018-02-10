package com.aditya.hibernate.hibernate5.util;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import com.aditya.hibernate.hibernate5.model.Employee;

public class Hibernate5Util {
	
	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;
	
	private Hibernate5Util() {
		try {

			// Create registry builder
			//StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder().configure();
			StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

			// Hibernate settings equivalent to hibernate.cfg.xml's properties
			Map<String, String> settings = new HashMap<>();
			settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
			settings.put(Environment.URL, "jdbc:mysql://localhost:3306/userdb");
			settings.put(Environment.USER, "root");
			settings.put(Environment.PASS, "maa2319");
			settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL57Dialect");
			settings.put(Environment.SHOW_SQL, "true");
			settings.put(Environment.HBM2DDL_AUTO, "update");
			settings.put(Environment.FORMAT_SQL, "true");

			// Apply Settings
			registryBuilder.applySettings(settings);

			// Create registry
			registry = registryBuilder.build();

			// Create MetadataSources
			MetadataSources sources = new MetadataSources(registry);

			sources.addAnnotatedClass(Employee.class);

			// Create Metadata
			Metadata metadata = sources.getMetadataBuilder().build();

			// Create SessionFactory
			sessionFactory = metadata.getSessionFactoryBuilder().build();

		} catch (Exception e) {
			e.printStackTrace();
			if (registry != null) {
				StandardServiceRegistryBuilder.destroy(registry);
			}
		}
	}
	
	private static class InnerClass{
		private static final Hibernate5Util INSTANCE= new Hibernate5Util();
	}

	public static Hibernate5Util getInstance() {
		return InnerClass.INSTANCE;
		
	}
	
	public static SessionFactory getSessionFactory() {
		return getInstance().sessionFactory;
	}
	public static void shutdown() {
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

	
	

}
