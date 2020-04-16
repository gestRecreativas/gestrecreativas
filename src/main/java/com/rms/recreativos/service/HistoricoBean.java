package com.rms.recreativos.service;

import java.util.List;

import com.rms.recreativos.entity.Establecimiento;
import com.rms.recreativos.entity.Ticket;

public class HistoricoBean {

	private Establecimiento establecimiento;
	private List<Ticket> tickets;
	private String fechaInicioFiltro;
	private String fechaFinFiltro;
	
	public HistoricoBean(Establecimiento establecimiento, List<Ticket> tickets) {
		super();
		this.establecimiento = establecimiento;
		this.tickets = tickets;
	}

	public Establecimiento getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(Establecimiento establecimiento) {
		this.establecimiento = establecimiento;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public String getFechaInicioFiltro() {
		return fechaInicioFiltro;
	}

	public void setFechaInicioFiltro(String fechaInicioFiltro) {
		this.fechaInicioFiltro = fechaInicioFiltro;
	}

	public String getFechaFinFiltro() {
		return fechaFinFiltro;
	}

	public void setFechaFinFiltro(String fechaFinFiltro) {
		this.fechaFinFiltro = fechaFinFiltro;
	}
}