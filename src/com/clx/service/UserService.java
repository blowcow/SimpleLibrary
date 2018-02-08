package com.clx.service;

import java.util.List;

import com.clx.model.Book;
import com.clx.model.Role;
import com.clx.model.User;

public interface UserService {

    User login(User user) throws Exception;
	boolean register(User user);
	boolean nameIsExist(String name);
	boolean delectUser(User user) throws Exception;
	boolean changeRole(User user);
	boolean lendBook(User user,Book book);
	boolean backBook(User user,Book book);
	String findPassfirst(String name) throws Exception;
	String findPassSecond(String name,String answer);
	List getUser(int offset,int length);
}
