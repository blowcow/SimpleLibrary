package com.clx.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.clx.model.User;
import com.clx.service.UserServiceImp;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Component("useractionbean")
public class UserAction extends ActionSupport implements ModelDriven<User>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	@Resource(name="userserviceimpproxy")
	private UserServiceImp userserviceimpproxy;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserServiceImp getUserserviceimpproxy() {
		return userserviceimpproxy;
	}

	public void setUserserviceimpproxy(UserServiceImp userserviceimpproxy) {
		this.userserviceimpproxy = userserviceimpproxy;
	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	public  void changerole(){
		userserviceimpproxy.changeRole(user);
	}
}
