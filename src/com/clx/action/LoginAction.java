
package com.clx.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;

import com.clx.model.User;
import com.clx.service.UserServiceImp;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author PVer
 *
 */
public class LoginAction extends ActionSupport implements ModelDriven {

	
	private static final long serialVersionUID = 1L;
	private UserServiceImp userserviceimp;
	private User user;
	
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public UserServiceImp getUserserviceimp() {
		return userserviceimp;
	}

	public void setUserserviceimp(UserServiceImp userserviceimp) {
		this.userserviceimp = userserviceimp;
	}

	public String login() throws Exception {
		User getuser = userserviceimp.login(user);
		if(getuser==null) return "input";
		ActionContext.getContext().getSession().put("user", getuser);
		return "success";
		}
	
	public String  register() throws IOException {
		boolean register = userserviceimp.register(user);
		if (register) {
			 return "success";
		}
		return "input";
	}
	
	public String loginout () {
		ActionContext.getContext().getSession().clear();
		return "input";
	}
	
	public void testname() throws IOException{
		String name = ServletActionContext.getRequest().getParameter("name");
		boolean nameIsExist = userserviceimp.nameIsExist(name);
		ServletActionContext.getResponse().setCharacterEncoding("utf8");
		if(nameIsExist) {
			ServletActionContext.getResponse().getWriter().print("该名称已被使用");
		}
		else ServletActionContext.getResponse().getWriter().print("该名称可以使用");
	}
	
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

}
