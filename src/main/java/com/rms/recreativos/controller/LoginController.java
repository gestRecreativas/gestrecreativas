package com.rms.recreativos.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rms.recreativos.util.SessionUtil;

@Controller
public class LoginController {

	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	@RequestMapping(value={"/login.html"})
	public String webLogin(HttpServletRequest request, ModelMap model){
		HttpSession session = request.getSession();
		SessionUtil.initSession(session);
		return "login";
	}
}