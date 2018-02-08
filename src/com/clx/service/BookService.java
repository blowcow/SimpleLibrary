package com.clx.service;

import java.util.List;

import com.clx.model.Book;

public interface BookService {
	boolean addBook(Book book);
	boolean clearBook(Book book);
	void updateBook(int bookid,String name,String author,double price,int count);
	List<Book> getBook(int offset,int length);
	int getcount();
	
}
