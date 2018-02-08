package com.clx.service;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.clx.model.Book;
import com.clx.model.User;

public class BookServiceImp implements BookService {
	
	private HibernateTemplate hibernatetemplate;
	
	
	public HibernateTemplate getHibernatetemplate() {
		return hibernatetemplate;
	}

	public void setHibernatetemplate(HibernateTemplate hibernatetemplate) {
		this.hibernatetemplate = hibernatetemplate;
	}

	@Override
	public boolean addBook(Book book) {
		// TODO Auto-generated method stub
		hibernatetemplate.save(book);
		return true;
	}

	@Override
	public boolean clearBook(Book book) {
		// TODO Auto-generated method stub
		hibernatetemplate.delete(book);
		return true;
	}

	@Override
	public void updateBook(int bookid,String name,String author,double price,int count) {
		// TODO Auto-generated method stub
		Book book = hibernatetemplate.get(Book.class, bookid);
		book.setName(name);
		book.setAuthor(author);
		book.setPrice(price);
		book.setCount(count);
	}

	public List<Book> getBook(int offset, int length) {
		// TODO Auto-generated method stub
		List<Book> list = hibernatetemplate.execute(new HibernateCallback<List<Book>>() {

			@Override
			public List<Book> doInHibernate(Session arg0) throws HibernateException {
				// TODO Auto-generated method stub
				Query<Book> query = arg0.createQuery("from Book book");
				query.setFirstResult(offset);
				query.setMaxResults(length);
				return query.list();
			}
		});
		
		return list;
	}

	@Override
	public int getcount() {
		 int integer = hibernatetemplate.execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session arg0) throws HibernateException {
				// TODO Auto-generated method stub
				Query query = arg0.createQuery("select count(*) from Book b");
				int i = query.getFirstResult();
				return i;
			}
		});
		 return integer;
	}
	
	public boolean downBookStock(Book book,int i){
		Book bookp = hibernatetemplate.get(Book.class, book.getBookid());
		int count = bookp.getCount();
		if(count==0) return false;
		bookp.setCount(count-i);
		return true;
	}
	
	public boolean upBookStock(Book book,int i){
		Book bookp = hibernatetemplate.get(Book.class, book.getBookid());
		int count = bookp.getCount();
		bookp.setCount(count+i);
		return true;
	}
	
	public Book getBookById(int bookid) {
		String hql="from Book b left outer join fetch b.user where b.bookid =?";
		List<Book> find = (List<Book>) hibernatetemplate.find(hql, bookid);
		
		if(find.size()>0)return find.get(0);
		else return null;
	}
}
