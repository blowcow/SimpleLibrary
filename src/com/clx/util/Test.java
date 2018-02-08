package com.clx.util;

import java.util.HashSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.mapping.Set;
import org.hibernate.query.Query;
import org.springframework.core.MethodIntrospector;

import com.clx.model.Book;
import com.clx.model.Role;
import com.clx.model.User;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		User user = new User();
		user.setUserid(1);
		
		
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
//		
		
		
		
		session.getTransaction().commit();
		session.close();
		
		
	}

}
