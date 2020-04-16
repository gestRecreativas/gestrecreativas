package com.rms.recreativos.service;

import java.util.Date;

public class RecaudacionBean {

	private String empresaMaquina;
	private String establecimiento;
	private Long idEstablecimiento;
	private Date fecha;
	private String login;
	private Long idTicket;
	private String hora;
	
	
	public String getEmpresaMaquina() {
		return empresaMaquina;
	}
	
	public void setEmpresaMaquina(String empresaMaquina) {
		this.empresaMaquina = empresaMaquina;
	}
	
	public String getEstablecimiento() {
		return establecimiento;
	}
	
	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}
	
	public Long getIdEstablecimiento() {
		return idEstablecimiento;
	}

	public void setIdEstablecimiento(Long idEstablecimiento) {
		this.idEstablecimiento = idEstablecimiento;
	}

	public Date getFecha() {
		return fecha;
	}
	
		
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	public String getHora() {
		return hora;
	}
	
	public void setHora(String hora) {
		this.hora = hora;
	}
	
	public Long getIdTicket() {
		return idTicket;
	}
	
	public void setIdTicket(Long idTicket) {
		this.idTicket = idTicket;
	}
}