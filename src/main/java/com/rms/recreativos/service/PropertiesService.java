package com.rms.recreativos.service;

public class PropertiesService {

	private String jdbcUrl;
	private String jdbcUsername;
	private String jdbcPassword;
	private String rutaFicherosTicket;
	
	public String getJdbcUrl() {
		return jdbcUrl;
	}
	
	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}
	
	public String getJdbcUsername() {
		return jdbcUsername;
	}
	
	public void setJdbcUsername(String jdbcUsername) {
		this.jdbcUsername = jdbcUsername;
	}
	
	public String getJdbcPassword() {
		return jdbcPassword;
	}
	
	public void setJdbcPassword(String jdbcPassword) {
		this.jdbcPassword = jdbcPassword;
	}

	public String getRutaFicherosTicket() {
		return rutaFicherosTicket;
	}

	public void setRutaFicherosTicket(String rutaFicherosTicket) {
		this.rutaFicherosTicket = rutaFicherosTicket;
	}
}