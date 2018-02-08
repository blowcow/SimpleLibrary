package com.clx.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.clx.model.User;
import com.clx.threadlocal.LocalReqResp;
@Component
@org.aspectj.lang.annotation.Aspect
public class PrivilegeAspect {
	
	
//	用aop拦截action，虽然可行，但localresp的输出不能再页面上显示。
	public void managevalidate(ProceedingJoinPoint pjp) throws Throwable{
		HttpServletRequest localreq = LocalReqResp.getLocalreq();
		HttpServletResponse localresp = LocalReqResp.getLocalresp();
		localresp.setCharacterEncoding("utf8");
		User user = (User) localreq.getSession().getAttribute("user");
		if(user==null) {
			localresp.getWriter().write("<script type=\"text/javascript\">alert(\"请先登录\");</script>");
		}
		String role = user.getRole().getName();
		if(!role.equals("管理员")&&!role.equals("超级管理员")) {
			localresp.getWriter().write("<script type=\"text/javascript\">alert(\"你没有权限\");</script>");
			localresp.getWriter().flush();
			return;
		}
		pjp.proceed();
	}
	
	
}
