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
@Table(name = "CREDITO", schema = "GESTRECREATIVAS")
public class Credito implements java.io.Serializable {

	private Long idCredito;
	private Long idEstablecimiento;
	private Long idEmpresaMaquina;
	private Date fecha;
	private BigDecimal importeInicial;
	private BigDecimal importePendiente;
	private BigDecimal cuota;
	private Date fechaCobro;
	private Long idUsuario;
	private BigDecimal creditoInicialActual;

	
	@Id
    @Column(name = "ID_CREDITO", unique = true, nullable = false)
	public Long getIdCredito() {
		return idCredito;
	}
	
	public void setIdCredito(Long idCredito) {
		this.idCredito = idCredito;
	}
	
	@Column(name = "ID_ESTABLECIMIENTO", nullable = false)
	public Long getIdEstablecimiento() {
		return idEstablecimiento;
	}
	
	public void setIdEstablecimiento(Long idEstablecimiento) {
		this.idEstablecimiento = idEstablecimiento;
	}
	
	@Column(name = "ID_EMPRESA_MAQUINA", nullable = false)
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
	
	@Column(name = "IMPORTE_INICIAL", nullable = false)
	public BigDecimal getImporteInicial() {
		return importeInicial;
	}
	
	public void setImporteInicial(BigDecimal importeInicial) {
		this.importeInicial = importeInicial;
	}
	
	@Column(name = "IMPORTE_PENDIENTE", nullable = false)
	public BigDecimal getImportePendiente() {
		return importePendiente;
	}
	
	public void setImportePendiente(BigDecimal importePendiente) {
		this.importePendiente = importePendiente;
	}
	
	@Column(name = "CUOTA", nullable = false)
	public BigDecimal getCuota() {
		return cuota;
	}
	
	public void setCuota(BigDecimal cuota) {
		this.cuota = cuota;
	}
	
	@Column(name = "CREDITOINICIALACTUAL", nullable = false)
	public BigDecimal getCreditoInicialActual() {
		return creditoInicialActual;
	}
	
	public void setCreditoInicialActual(BigDecimal creditoInicialActual) {
		this.creditoInicialActual = creditoInicialActual;
	}

	@Column(name = "ID_USUARIO", nullable = false)
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	@Column(name = "FECHACOBRO", nullable = false)
	public Date getFechaCobro() {
		return fechaCobro;
	}
	
	public void setFechaCobro(Date fechaCobro) {
		this.fechaCobro = fechaCobro;
	}
}