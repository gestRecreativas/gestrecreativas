package com.rms.recreativos.service;

import java.math.BigDecimal;

import com.rms.recreativos.entity.Contador;
import com.rms.recreativos.entity.Establecimiento;
import com.rms.recreativos.entity.Liquidacion;
import com.rms.recreativos.entity.Maquina;

public class MaquinaBean {

	private Maquina maquina;
	private Establecimiento establecimiento;
	private Contador contadorAnt;
	private Contador contadorActual;
	private Liquidacion liquidacionActual;
	private BigDecimal porcentajeEstablecimiento;
	private BigDecimal retencionAcumuladaAnio;
	private boolean hayTicketActual;
	
	public MaquinaBean(Maquina maquina, Establecimiento establecimiento){
		this.maquina = maquina;
		this.establecimiento = establecimiento;
	}

	public Maquina getMaquina() {
		return maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	public Establecimiento getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(Establecimiento establecimiento) {
		this.establecimiento = establecimiento;
	}

	public Contador getContadorAnt() {
		return contadorAnt;
	}

	public void setContadorAnt(Contador contadorAnt) {
		this.contadorAnt = contadorAnt;
	}

	public Contador getContadorActual() {
		return contadorActual;
	}

	public void setContadorActual(Contador contadorActual) {
		this.contadorActual = contadorActual;
	}

	public Liquidacion getLiquidacionActual() {
		return liquidacionActual;
	}

	public void setLiquidacionActual(Liquidacion liquidacionActual) {
		this.liquidacionActual = liquidacionActual;
	}

	public BigDecimal getPorcentajeEstablecimiento() {
		return porcentajeEstablecimiento;
	}

	public void setPorcentajeEstablecimiento(BigDecimal porcentajeEstablecimiento) {
		this.porcentajeEstablecimiento = porcentajeEstablecimiento;
	}

	public BigDecimal getRetencionAcumuladaAnio() {
		return retencionAcumuladaAnio;
	}

	public void setRetencionAcumuladaAnio(BigDecimal retencionAcumuladaAnio) {
		this.retencionAcumuladaAnio = retencionAcumuladaAnio;
	}

	public boolean isHayTicketActual() {
		return hayTicketActual;
	}

	public void setHayTicketActual(boolean hayTicketActual) {
		this.hayTicketActual = hayTicketActual;
	}
}