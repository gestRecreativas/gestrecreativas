package com.rms.recreativos.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	private static final Logger logger = Logger.getLogger(IndexController.class);
	
	@RequestMapping(value={"/index.html"})
	public String webIndex(HttpServletRequest request, ModelMap model){
		return "index";
	}
}