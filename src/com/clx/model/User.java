package com.clx.model;

import java.util.Set;

public class User {
	
	private int userid;
	private String name;
	private String pass;
	private String question;
	private String answer;
	private Role role;
	private Set<Book> books;
	
	

	public User(int userid, String name, String pass, Role role) {
		super();
		this.userid = userid;
		this.name = name;
		this.pass = pass;
		this.role = role;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	


}
