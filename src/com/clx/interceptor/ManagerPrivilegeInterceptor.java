package com.clx.interceptor;

import com.clx.model.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class ManagerPrivilegeInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation ai) throws Exception {
		// TODO Auto-generated method stub
		
		User user = (User) ai.getInvocationContext().getSession().get("user");
		String role = user.getRole().getName();
		if (role.equals("管理员")||role.equals("超级管理员")) {
			ai.invoke();
		}
		return null; //如果action中未返回值取得视图，struts2才会根据拦截器的返回值去取视图。
	}

}
