package com.clx.interceptor;

import com.clx.action.LoginAction;
import com.clx.model.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		// TODO Auto-generated method stub
		User user = (User) ai.getInvocationContext().getSession().get("user");
		System.out.println("interceptor!!"+ai.getAction().getClass().getName());
		if(user==null && !ai.getAction().getClass().getName().equals("com.clx.action.LoginAction")) {
			return "input";
		}
		ai.invoke();
		return null;
	}

}
