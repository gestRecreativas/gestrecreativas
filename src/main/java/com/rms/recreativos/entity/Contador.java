package com.rms.recreativos.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CONTADOR", schema = "GESTRECREATIVAS")
public class Contador implements java.io.Serializable {

	private Long idContador;
	private Long idMaquina;
	private Date fecha;
	private BigInteger entrada;
	private BigInteger salida;
	private String comentarios;
	private Long idUsuario;
	private Long idTicket;
	private Long idEstablecimiento;
	
	@Id
    @Column(name = "ID_CONTADOR", unique = true, nullable = false)
	public Long getIdContador() {
		return idContador;
	}

	public void setIdContador(Long idContador) {
		this.idContador = idContador;
	}

	@Column(name = "ID_MAQUINA", nullable = false)
	public Long getIdMaquina() {
		return idMaquina;
	}
	
	public void setIdMaquina(Long idMaquina) {
		this.idMaquina = idMaquina;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA", nullable = false)
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	@Column(name = "ENTRADA", nullable = false)
	public BigInteger getEntrada() {
		return entrada;
	}
	
	public void setEntrada(BigInteger entrada) {
		this.entrada = entrada;
	}
	
	@Column(name = "SALIDA", nullable = false)
	public BigInteger getSalida() {
		return salida;
	}
	
	public void setSalida(BigInteger salida) {
		this.salida = salida;
	}
	
	@Column(name = "COMENTARIOS", length = 500)
	public String getComentarios() {
		return comentarios;
	}
	
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	
	@Column(name = "ID_USUARIO", nullable = false)
	public Long getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Column(name = "ID_TICKET")
	public Long getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(Long idTicket) {
		this.idTicket = idTicket;
	}
	

	@Column(name = "ID_ESTABLECIMIENTO")
	public Long getIdEstablecimiento() {
		return idEstablecimiento;
	}

	public void setIdEstablecimiento(Long idEstablecimiento) {
		this.idEstablecimiento = idEstablecimiento;
	}
}