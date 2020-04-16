package com.rms.recreativos.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.rms.recreativos.entity.Credito;
import com.rms.recreativos.entity.EmpresaMaquina;
import com.rms.recreativos.entity.Establecimiento;
import com.rms.recreativos.entity.Maquina;
import com.rms.recreativos.entity.Ticket;

public class TicketBean {

	private EmpresaMaquina empresaMaquina;
	private Establecimiento establecimiento;
	private List<Maquina> maquinas;
	private Credito credito;
	private LinkedHashMap<Long, InfoTicketMaquinaBean> infoMaquinas = new LinkedHashMap<Long, InfoTicketMaquinaBean>();
	private Ticket ticket;
	private String fechaInicioFiltro;
	private String fechaFinFiltro;
	private String fechaRecaudacion;
	private String ficheroTxt;
	
	public TicketBean(EmpresaMaquina empresaMaquina, Establecimiento establecimiento, List<Maquina> maquinas, Credito credito) {
		super();
		this.empresaMaquina = empresaMaquina;
		this.establecimiento = establecimiento;
		this.maquinas = maquinas;
		this.credito = credito;
	}

	public EmpresaMaquina getEmpresaMaquina() {
		return empresaMaquina;
	}

	public void setEmpresaMaquina(EmpresaMaquina empresaMaquina) {
		this.empresaMaquina = empresaMaquina;
	}

	public Establecimiento getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(Establecimiento establecimiento) {
		this.establecimiento = establecimiento;
	}

	public List<Maquina> getMaquinas() {
		return maquinas;
	}

	public void setMaquinas(List<Maquina> maquinas) {
		this.maquinas = maquinas;
	}

	public Credito getCredito() {
		return credito;
	}

	public void setCredito(Credito credito) {
		this.credito = credito;
	}

	public LinkedHashMap<Long, InfoTicketMaquinaBean> getInfoMaquinas() {
		return infoMaquinas;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
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

	public String getFechaRecaudacion() {
		return fechaRecaudacion;
	}

	public void setFechaRecaudacion(String fechaRecaudacion) {
		this.fechaRecaudacion = fechaRecaudacion;
	}

	public String getFicheroTxt() {
		return ficheroTxt;
	}

	public void setFicheroTxt(String ficheroTxt) {
		this.ficheroTxt = ficheroTxt;
	}

	public void putInfoMaquina(Long idMaquina, InfoTicketMaquinaBean infoTicketMaquinaBean){
		infoMaquinas.put(idMaquina, infoTicketMaquinaBean);
	}
}