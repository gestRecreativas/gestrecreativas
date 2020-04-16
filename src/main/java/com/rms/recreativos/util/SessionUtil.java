package com.rms.recreativos.util;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.rms.recreativos.entity.Usuario;
import com.rms.recreativos.service.SessionBean;

public class SessionUtil {
	
	private static final Logger logger = Logger.getLogger(SessionUtil.class);
	
	private final static String PRINCIPAL_SESSION_OBJECT = "principalSession";
	
	public static SessionBean initSession(HttpSession session){
		SessionBean sessionBean = new SessionBean();
		session.setAttribute(PRINCIPAL_SESSION_OBJECT, sessionBean);
		return sessionBean;
	}
	
	public static void updateSession(HttpSession session, Usuario usuario, boolean logged, boolean admin){
		SessionBean sessionBean = (SessionBean) session.getAttribute(PRINCIPAL_SESSION_OBJECT);
		if (sessionBean == null){
			sessionBean = initSession(session);
		}
		sessionBean.setLogged(logged);
		sessionBean.setAdmin(admin);
		sessionBean.setUsuario(usuario);
		session.setAttribute(PRINCIPAL_SESSION_OBJECT, sessionBean);
	}
	
	public static void cleanSession(HttpSession session){
		session.setAttribute(PRINCIPAL_SESSION_OBJECT, null);
	}
	
	public static boolean isLogged(HttpSession session){
		SessionBean sessionBean = (SessionBean) session.getAttribute(PRINCIPAL_SESSION_OBJECT);
		return sessionBean != null && sessionBean.isLogged();
	}
	
	public static boolean isAdmin(HttpSession session){
		SessionBean sessionBean = (SessionBean) session.getAttribute(PRINCIPAL_SESSION_OBJECT);
		return sessionBean != null && sessionBean.isAdmin();
	}
	
	public static Usuario getUsuario(HttpSession session){
		SessionBean sessionBean = (SessionBean) session.getAttribute(PRINCIPAL_SESSION_OBJECT);
		if (sessionBean != null){
			return sessionBean.getUsuario();
		} else {
			return null;
		}
	}
}