package com.harvey.Util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.runners.model.InitializationError;


public class HibernateUtil {

	private static Configuration configuration = null;
	
	private static SessionFactory sessionfactory= null;
	
	private static Session session = null;
	
	static{
		try{
			//create sessionfactory at initial period
			configuration = new Configuration().configure();
			
			sessionfactory =  configuration.buildSessionFactory(new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.build());
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	
	
	/**
	 * get a session
	 * @return
	 */
	public static Session getSession(){
		if(sessionfactory!=null){
			session = sessionfactory.openSession();
			return session;
		}
		try{
			//create session-factory if session-factory is null
			sessionfactory =  configuration.buildSessionFactory(new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.build());
			session = sessionfactory.openSession();
			}catch(HibernateException e){
				e.printStackTrace();
			}
		
		return session;
	}
	
	/**
	 * close session
	 */
	public static void closeSession(){
		if(session!=null && session.isOpen()){
			session.close();
		}
	}
	
	/**
	 * close sessionFactory
	 */
	public static void closeSessionFactory(){
		if(sessionfactory!=null){
			sessionfactory.close();
		}
	}
	
	
}
