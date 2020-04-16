package com.rms.recreativos.service;

public class AdminMaquinasBean {

	private Long idMaquina;
	private String nombre;
	private String establecimiento;
	
	public Long getIdMaquina() {
		return idMaquina;
	}
	
	public void setIdMaquina(Long idMaquina) {
		this.idMaquina = idMaquina;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getEstablecimiento() {
		return establecimiento;
	}
	
	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}
}