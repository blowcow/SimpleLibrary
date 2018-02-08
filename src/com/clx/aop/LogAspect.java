package com.clx.aop;

import java.util.Date;

import org.aspectj.lang.JoinPoint;

import com.clx.model.User;

public class LogAspect {
//	private Logger log = Logger.getLogger("userlog");
	public LogAspect( ) {
		
	}
	public void before(JoinPoint jp) {
		String methodname = jp.getSignature().getName();
		String classname = jp.getSignature().getDeclaringTypeName();
		System.out.println("类；"+classname);
		System.out.println("方法；"+methodname);
		System.out.println(new Date());
	}
	
	
}
