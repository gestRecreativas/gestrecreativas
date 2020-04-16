package com.rms.recreativos.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TICKET", schema = "GESTRECREATIVAS")
public class Ticket implements java.io.Serializable {

	private Long idTicket;
	private Long idEstablecimiento;
	private Long idEmpresaMaquina;
	private Date fecha;
	private BigDecimal total;
	private BigDecimal retencion;
	private BigDecimal establecimiento;
	private BigDecimal empresa;
	private BigDecimal neto;
	private Long idCredito;
	private BigDecimal creditoPendienteInicial;
	private BigDecimal cuotaCredito;
	private Long idUsuario;
	private Long completo;
	private String hora;
	
	@Id
    @Column(name = "ID_TICKET", unique = true, nullable = false)
	public Long getIdTicket() {
		return idTicket;
	}
	
	public void setIdTicket(Long idTicket) {
		this.idTicket = idTicket;
	}
	
	@Column(name = "ID_ESTABLECIMIENTO", nullable = false)
	public Long getIdEstablecimiento() {
		return idEstablecimiento;
	}
	
	public void setIdEstablecimiento(Long idEstablecimiento) {
		this.idEstablecimiento = idEstablecimiento;
	}
	
	@Column(name = "ID_EMPRESA_MAQUINA")
	public Long getIdEmpresaMaquina() {
		return idEmpresaMaquina;
	}

	public void setIdEmpresaMaquina(Long idEmpresaMaquina) {
		this.idEmpresaMaquina = idEmpresaMaquina;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA", nullable = false)
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	@Column(name = "TOTAL", nullable = false)
	public BigDecimal getTotal() {
		return total;
	}
	
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	@Column(name = "RETENCION", nullable = false)
	public BigDecimal getRetencion() {
		return retencion;
	}
	
	public void setRetencion(BigDecimal retencion) {
		this.retencion = retencion;
	}
	
	@Column(name = "ESTABLECIMIENTO", nullable = false)
	public BigDecimal getEstablecimiento() {
		return establecimiento;
	}
	
	public void setEstablecimiento(BigDecimal establecimiento) {
		this.establecimiento = establecimiento;
	}
	
	@Column(name = "EMPRESA", nullable = false)
	public BigDecimal getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(BigDecimal empresa) {
		this.empresa = empresa;
	}
	
	@Column(name = "NETO", nullable = false)
	public BigDecimal getNeto() {
		return neto;
	}
	
	public void setNeto(BigDecimal neto) {
		this.neto = neto;
	}
	
	@Column(name = "ID_CREDITO")
	public Long getIdCredito() {
		return idCredito;
	}

	public void setIdCredito(Long idCredito) {
		this.idCredito = idCredito;
	}
	
	@Column(name = "CREDITO_PEND_INICIAL")
	public BigDecimal getCreditoPendienteInicial() {
		return creditoPendienteInicial;
	}

	public void setCreditoPendienteInicial(BigDecimal creditoPendienteInicial) {
		this.creditoPendienteInicial = creditoPendienteInicial;
	}

	@Column(name = "CUOTA_CREDITO")
	public BigDecimal getCuotaCredito() {
		return cuotaCredito;
	}

	public void setCuotaCredito(BigDecimal cuotaCredito) {
		this.cuotaCredito = cuotaCredito;
	}

	@Column(name = "ID_USUARIO", nullable = false)
	public Long getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Column(name = "COMPLETO", precision = 1, scale = 0)
	public Long getCompleto() {
		return completo;
	}

	public void setCompleto(Long completo) {
		this.completo = completo;
	}
	
	@Column(name = "HORA", length = 20, nullable = false)
	public String getHora() {
		return hora;
	}
	
	public void setHora(String hora) {
		this.hora = hora;
	}
	
}