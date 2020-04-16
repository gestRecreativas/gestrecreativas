package com.rms.recreativos.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rms.recreativos.entity.Usuario;
import com.rms.recreativos.service.RecreativosService;
import com.rms.recreativos.util.SessionUtil;

@Controller
public class LoginActionController {

	private static final Logger logger = Logger.getLogger(LoginActionController.class);
	
	@Autowired
	private RecreativosService rs;
	
	@RequestMapping(value={"/loginAction.html"})
	public ModelAndView webLoginAction(HttpServletRequest request, ModelMap model, 
				@RequestParam(value="us", defaultValue="") String us,
				@RequestParam(value="pwd", defaultValue="") String pwd){
		Usuario usuario = rs.getUsuarioByLoginPassword(us, pwd);
		if (usuario != null){
			HttpSession session = request.getSession();
			SessionUtil.updateSession(session, usuario, true, usuario.getAdmin() == 1? true : false);
			logger.debug("Logado y redirige a index");
			return new ModelAndView("redirect", "pagina", "index.html");
		} else {
			return new ModelAndView("redirect", "pagina", "login.html");
		}
	}
	
	@RequestMapping(value={"/logout.html"})
	public ModelAndView webLoginAction(HttpServletRequest request, ModelMap model){
		HttpSession session = request.getSession();
		SessionUtil.cleanSession(session);
		return new ModelAndView("redirect", "pagina", "login.html");
	}
}