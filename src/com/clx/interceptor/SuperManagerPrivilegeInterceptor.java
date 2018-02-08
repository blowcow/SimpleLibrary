package com.clx.interceptor;

import com.clx.model.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class SuperManagerPrivilegeInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation ai) throws Exception {
		// TODO Auto-generated method stub
		User user = (User) ai.getInvocationContext().getSession().get("user");
		String role = user.getRole().getName();
		if (role.equals("超级管理员")) {
			ai.invoke();
		}
		return null;
	}

}
