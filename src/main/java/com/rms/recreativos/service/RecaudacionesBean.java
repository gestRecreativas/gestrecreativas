package com.rms.recreativos.service;

import java.util.Date;
import java.util.List;

public class RecaudacionesBean {

	private List<RecaudacionBean> recaudaciones;
	private Date fecha;
	
	public RecaudacionesBean(List<RecaudacionBean> recaudaciones, Date fecha) {
		super();
		this.recaudaciones = recaudaciones;
		this.fecha = fecha;
	}

	public List<RecaudacionBean> getRecaudaciones() {
		return recaudaciones;
	}
	
	public void setRecaudaciones(List<RecaudacionBean> recaudaciones) {
		this.recaudaciones = recaudaciones;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}