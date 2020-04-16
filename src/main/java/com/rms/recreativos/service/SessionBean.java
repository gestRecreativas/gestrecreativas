package com.rms.recreativos.service;

import com.rms.recreativos.entity.Usuario;

public class SessionBean {

	private boolean isLogged = false;
	private boolean isAdmin = false;
	private Usuario usuario;
	
	public boolean isLogged() {
		return isLogged;
	}
	
	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}
	
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}