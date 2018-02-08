package com.clx.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
private static SessionFactory sf =null;
private static ThreadLocal<Session> tl= new ThreadLocal<>();
static{
	buildSessionFactory();
}

public static Session getSession(){
	Session session = tl.get();
	if (session==null||!session.isOpen()) {
		if(sf==null){
			buildSessionFactory();
		}
		session = sf.openSession();
		tl.set(session);
	}
	return session;
}

public static void buildSessionFactory(){
	Configuration cf =  new Configuration().configure();
	sf = cf.buildSessionFactory();
}

public static SessionFactory getSf() {
	return sf;
}

public static void closeSession(){
	Session session = tl.get();
	if (session!=null) {
		
		session.close();
	}
	tl.set(null);
}

}
