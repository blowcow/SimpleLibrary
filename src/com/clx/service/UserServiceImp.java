package com.clx.service;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.clx.model.Book;
import com.clx.model.Role;
import com.clx.model.User;

public class UserServiceImp implements UserService{
	private HibernateTemplate hibernatetemplate; //线程安全，可重用，ioc注入

	public HibernateTemplate getHibernatetemplate() {
		return hibernatetemplate;
	}

	public void setHibernatetemplate(HibernateTemplate hibernatetemplate) {
		this.hibernatetemplate = hibernatetemplate;
	}

	public User login(User user) throws Exception {
		String hql="from User user left outer join fetch user.role where user.name=?";
		List users = hibernatetemplate.find(hql, user.getName());
		if(users.size()>1) throw new Exception("存在多个同名用户");
		if(users.isEmpty()) return null;
		User u = (User)users.get(0);
		if(u.getPass().equals(user.getPass())) {
			User user2 = new User();
			user2.setUserid(u.getUserid());
			user2.setName(u.getName());
			user2.setRole(u.getRole());
			return user2;
		}
		return null;
	}
	
	public boolean register(User user) {
		
		if(nameIsExist(user.getName())) return false; 
		Role role = new Role();
		role.setRoleid(1);
		user.setRole(role);
		hibernatetemplate.save(user);
		return true;
		
	}

	public boolean nameIsExist(String name) {
		String hql="from User user where user.name=?";
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) hibernatetemplate.find(hql, name);
		if(users.isEmpty())return false;
		return true;
	}
	
	public boolean delectUser(User user) throws Exception{
		User userp = hibernatetemplate.get(User.class, user.getUserid());
		if(userp==null) throw new Exception("用户不存在");
		hibernatetemplate.delete(userp);
		return true;
	}
	
	public boolean changeRole(User user){
		User userp = hibernatetemplate.get(User.class, user.getUserid());
		List<Role> rolep = hibernatetemplate.findByExample(user.getRole(), 0, 1);
		userp.setRole(rolep.get(0));
		hibernatetemplate.saveOrUpdate(userp);
		return true;
	}
	
	public boolean qualifyaddBook(User user,Book book){
		User userp = hibernatetemplate.load(User.class, user.getUserid());
		Set<Book> books = userp.getBooks();
		if(books.size()>=5) return false;
		for (Book singlebook : books) {
			if(singlebook.getBookid()==book.getBookid()) {
				return false;
			}
		}
		return true;
	}
	@Override
	public boolean lendBook(User user, Book book) {
		// TODO Auto-generated method stub
		User userp = hibernatetemplate.load(User.class, user.getUserid());
		userp.getBooks().add(book);
		return true;
	}
	
	public boolean backBook(User user,Book book){
		User userp = hibernatetemplate.load(User.class, user.getUserid());
		Set<Book> books = userp.getBooks();
		if(books.size()==0) return false;
		for (Book singlebook : books) {
			if(singlebook.getBookid()==book.getBookid()) {
				books.remove(singlebook);
				userp.setBooks(books);
				return true;
			}
		}
		return false;
	}
	
	public String findPassfirst(String name) throws Exception {
		String hql="from User user where user.name=?";
		List<User> users = (List<User>) hibernatetemplate.find(hql, name);
		if(users.isEmpty())return "用户不存在";
		if(users.size()>1) throw new Exception(name+"密码找回时存在重名用户");
		if(users.get(0).getQuestion()==null||users.get(0).getQuestion()=="")return "未设置问题";
		return users.get(0).getQuestion();
	}
	
	
	public String findPassSecond(String name,String answer) {
		String hql="from User user where user.name=?";
		List<User> users = (List<User>) hibernatetemplate.find(hql, name);
		
		if(users.get(0).getAnswer()==answer) return users.get(0).getPass();
		return "回答错误！";
	}

	@Override
	public List<User> getUser(int offset, int length) {
		// TODO Auto-generated method stub
		List<User> list = hibernatetemplate.execute(new HibernateCallback<List<User>>() {

			@Override
			public List<User> doInHibernate(Session arg0) throws HibernateException {
				// TODO Auto-generated method stub
				Query<User> query = arg0.createQuery("from User u left outer join fetch u.role order by u.id");
				query.setFirstResult(offset);
				query.setMaxResults(length);
				return query.list();
			}
		});
		return list;
	}
	
	public List<?> getMyBook(User user) {
		List<?> find = hibernatetemplate.find("from Book b inner join fetch b.user u where u.userid=? ",user.getUserid());
		return find;
	}
	
}
