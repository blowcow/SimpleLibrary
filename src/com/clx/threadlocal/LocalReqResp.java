package com.clx.threadlocal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

public class LocalReqResp {
	
	private static ThreadLocal<HttpServletRequest> localreq=new ThreadLocal<>();
	private static ThreadLocal<HttpServletResponse> localresp =new ThreadLocal<>();
	
	private LocalReqResp() {}
	
	public static HttpServletRequest getLocalreq() {
		return localreq.get();
	}
	public static HttpServletResponse getLocalresp() {
		return localresp.get();
	}
	public static void setLocalreq(HttpServletRequest req) {
		localreq.set(req);
	}
	public static void setLocalresp(HttpServletResponse resp) {
		localresp.set(resp);
	}
	
	
}
