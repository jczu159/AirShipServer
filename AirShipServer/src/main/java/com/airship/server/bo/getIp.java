package com.airship.server.bo;

import javax.servlet.http.HttpServletRequest;

public class getIp {
	
	public  static String getIpAddr(HttpServletRequest request) {    
	    String ip = request.getHeader("x-forwarded-for");       
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {       
	        ip = request.getHeader("Proxy-Client-IP");       
	    }       
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {       
	        ip = request.getHeader("WL-Proxy-Client-IP");       
	    }       
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {       
	        ip = request.getRemoteAddr();       
	    }       
	    System.out.println("取得ip");
	    return ip;       
	}
}
