package com.clx.action;

import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.clx.model.Book;
import com.clx.model.User;
import com.clx.service.BookServiceImp;
import com.clx.service.UserServiceImp;
import com.clx.util.PageUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ShowAction extends ActionSupport {
	
	private String menuitem;
	private int pageAppoint;
	private List pageList;
	private String pageBottomBar;
	private PageUtil pageUtil;
	private UserServiceImp userserviceimp;
	private BookServiceImp bookserviceimp;
	
	public UserServiceImp getUserserviceimp() {
		return userserviceimp;
	}

	public void setUserserviceimp(UserServiceImp userserviceimp) {
		this.userserviceimp = userserviceimp;
	}

	public BookServiceImp getBookserviceimp() {
		return bookserviceimp;
	}

	public void setBookserviceimp(BookServiceImp bookserviceimp) {
		this.bookserviceimp = bookserviceimp;
	}

	public PageUtil getPageUtil() {
		return pageUtil;
	}

	public void setPageUtil(PageUtil pageUtil) {
		this.pageUtil = pageUtil;
	}

	public String getMenuitem() {
		return menuitem;
	}

	public void setMenuitem(String menuitem) {
		this.menuitem = menuitem;
	}

	public int getPageAppoint() {
		return pageAppoint;
	}

	public void setPageAppoint(int pageAppoint) {
		this.pageAppoint = pageAppoint;
	}

	public List getPageList() {
		return pageList;
	}

	public void setPageList(List pageList) {
		this.pageList = pageList;
	}

	public String getPageBottomBar() {
		return pageBottomBar;
	}

	public void setPageBottomBar(String pageBottomBar) {
		this.pageBottomBar = pageBottomBar;
	}

	public String execute(){
		if (menuitem==null||menuitem.equals("booklist")||menuitem.equals("")) {
			return showbooklist();
		}
		else if (menuitem.equals("mybook")) {
			return showmybook();
		}
		else if (menuitem.equals("managebook")) {
			return showmanagebook();
		}
		else if (menuitem.equals("manageuser")) {
			return showmanageuser();
		}
		else if (menuitem.equals("privilege")) {
			return showprivilege();
		}
		return menuitem;
	}
	
	public String showprivilege() {
		// TODO Auto-generated method stub
		return "privilege";
	}
	public String showmanageuser() {
		// TODO Auto-generated method stub
		int pageMaxCount = pageUtil.getPageMaxCount("User", 10);
		pageAppoint=pageAppoint<1?1:pageAppoint;
		pageAppoint=pageAppoint>pageMaxCount?pageMaxCount:pageAppoint;
		int offset = (pageAppoint-1)*10;
		pageList = userserviceimp.getUser(offset, 10);
		pageBottomBar= pageUtil.getBottomBar(pageMaxCount, pageAppoint, ServletActionContext.getRequest().getContextPath()+"/ShowAction?menuitem=manageuser&");
		return "manageuser";
	}
	public String showmanagebook() {
		// TODO Auto-generated method stub
		return "managebook";
	}
	public String showmybook() {
		// TODO Auto-generated method stub
		User user = (User) ActionContext.getContext().getSession().get("user");
		List<?> books = userserviceimp.getMyBook(user);
		pageList = books;
		return "mybook";
	}
	public String showbooklist() {
		// TODO Auto-generated method stub
		int pageMaxCount = pageUtil.getPageMaxCount("Book", 10);
		pageAppoint=pageAppoint<1?1:pageAppoint;
		pageAppoint=pageAppoint>pageMaxCount?pageMaxCount:pageAppoint;
		int offset = (pageAppoint-1)*10;
		pageList = bookserviceimp.getBook(offset, 10);
		pageBottomBar= pageUtil.getBottomBar(pageMaxCount, pageAppoint, ServletActionContext.getRequest().getContextPath()+"/ShowAction?menuitem=booklist&");
		return "booklist";
	}
		
	
}
