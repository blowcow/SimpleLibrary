package com.clx.action;

import java.io.IOException;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.clx.model.Book;
import com.clx.model.User;
import com.clx.service.BookServiceImp;
import com.clx.service.UserServiceImp;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BookAction2 extends ActionSupport {
	private int bookid;
	private String name;
	private String author;
	private double price;
	private int count;
	private UserServiceImp userserviceimpproxy;
	private BookServiceImp bookserviceimpproxy;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public UserServiceImp getUserserviceimpproxy() {
		return userserviceimpproxy;
	}

	public void setUserserviceimpproxy(UserServiceImp userserviceimpproxy) {
		this.userserviceimpproxy = userserviceimpproxy;
	}

	public BookServiceImp getBookserviceimpproxy() {
		return bookserviceimpproxy;
	}

	public void setBookserviceimpproxy(BookServiceImp bookserviceimpproxy) {
		this.bookserviceimpproxy = bookserviceimpproxy;
	}

	public void lendbook() throws IOException {
		User user = (User) ActionContext.getContext().getSession().get("user");
		ServletActionContext.getResponse().setCharacterEncoding("utf8");
		Book book = new Book();
		book.setBookid(bookid);
		boolean flag = userserviceimpproxy.qualifyaddBook(user, book);
		if (!flag) {ServletActionContext.getResponse().getWriter().write("借阅失败，原因：超过五本，或者已经借阅该书");return;}
		boolean flag2 = bookserviceimpproxy.downBookStock(book, 1);
		if (!flag2) {
			ServletActionContext.getResponse().getWriter().write("借阅失败，原因：库存不足");return;
		}
		boolean flag3 = userserviceimpproxy.lendBook(user, book);
		if(flag3){
			
			ServletActionContext.getResponse().getWriter().write("借阅成功！");
		}
	}
	
	public void backbook() throws IOException {
		User user = (User) ActionContext.getContext().getSession().get("user");
		ServletActionContext.getResponse().setCharacterEncoding("utf8");
		Book book = new Book();
		book.setBookid(bookid);
		boolean flag = userserviceimpproxy.backBook(user, book);
		if (!flag) {
			ServletActionContext.getResponse().getWriter().write("你没有该本书！");
			return;
		}
		
		bookserviceimpproxy.upBookStock(book, 1);
		ServletActionContext.getResponse().getWriter().write("成功归还！");
	}
	
	public void querybook() throws IOException{
		ServletActionContext.getResponse().setCharacterEncoding("utf8");
		Book bookp = bookserviceimpproxy.getBookById(bookid);
		if(bookp==null){
			ServletActionContext.getResponse().getWriter().write("没有这个ID号的书。。。");
			return;
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append("<form action=\"UpdateBook\" method=\"get\">");
		buffer.append("<input id=\"id\" name=\"name\" value=\""+bookp.getBookid()+"\" type=\"hidden\"/>");
		buffer.append("<td><input id=\"name\" name=\"name\" value=\""+bookp.getName()+"\"/></td>");
		buffer.append("<td><input id=\"author\" name=\"author\" value=\""+bookp.getAuthor()+"\"/></td>");
		buffer.append("<td><input id=\"price\" name=\"price\" value=\""+bookp.getPrice()+"\"/></td>");
		buffer.append("<td><input id=\"count\" name=\"count\" value=\""+bookp.getCount()+"\"/></td>");
		buffer.append("<td>");
		if (bookp.getUser()!=null) {
			for (User user : bookp.getUser()) {
				buffer.append(user.getName());
			}
		}
		buffer.append("</td>");
		buffer.append("<td><input type=\"submit\" value=\"修改\" onclick=\"updatebook()\"></td></from>");
		ServletActionContext.getResponse().getWriter().write(buffer.toString());
	}
	
	public void updatebook() throws IOException {
		bookserviceimpproxy.updateBook(bookid, name, author, price, count);
	}

}
