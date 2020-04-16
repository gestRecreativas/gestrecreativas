package com.rms.recreativos.service;

import com.rms.recreativos.entity.Credito;
import com.rms.recreativos.entity.Establecimiento;

public class EstablecimientoBean {

	private Establecimiento establecimiento;
	private Credito credito;
	
	public EstablecimientoBean(Establecimiento establecimiento, Credito credito) {
		super();
		this.establecimiento = establecimiento;
		this.credito = credito;
	}

	public Establecimiento getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(Establecimiento establecimiento) {
		this.establecimiento = establecimiento;
	}

	public Credito getCredito() {
		return credito;
	}

	public void setCredito(Credito credito) {
		this.credito = credito;
	}
}