package com.rms.recreativos.service;

import com.rms.recreativos.entity.Contador;
import com.rms.recreativos.entity.Liquidacion;

public class InfoTicketMaquinaBean {

	private Contador contador;
	private Contador contadorAnterior;
	private Liquidacion liquidacion;
	
	public InfoTicketMaquinaBean(Contador contador, Contador contadorAnterior, Liquidacion liquidacion) {
		super();
		this.contador = contador;
		this.contadorAnterior = contadorAnterior;
		this.liquidacion = liquidacion;
	}

	public Contador getContador() {
		return contador;
	}

	public void setContador(Contador contador) {
		this.contador = contador;
	}

	public Contador getContadorAnterior() {
		return contadorAnterior;
	}

	public void setContadorAnterior(Contador contadorAnterior) {
		this.contadorAnterior = contadorAnterior;
	}

	public Liquidacion getLiquidacion() {
		return liquidacion;
	}

	public void setLiquidacion(Liquidacion liquidacion) {
		this.liquidacion = liquidacion;
	}
}