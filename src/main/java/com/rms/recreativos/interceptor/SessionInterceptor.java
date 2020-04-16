package com.rms.recreativos.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.rms.recreativos.util.SessionUtil;

public class SessionInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = Logger.getLogger(SessionInterceptor.class);
	
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
    	HttpSession session = request.getSession();
    	if (!request.getRequestURI().contains("login.html") &&
    			!request.getRequestURI().contains("loginAction.html")){
	    	if (!SessionUtil.isLogged(session)){
	    		response.sendRedirect(request.getContextPath() + "/login.html");
	    		return false;
	    	}
    	}
    	return true;
    }
}