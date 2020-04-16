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
@Table(name = "LIQUIDACION", schema = "GESTRECREATIVAS")
public class Liquidacion implements java.io.Serializable {

	private Long idLiquidacion;
	private Long idMaquina;
	private Long idEstablecimiento;
	private Long idEmpresaMaquina;
	private Date fecha;
	private BigDecimal bruto;
	private BigDecimal fallos;
	private BigDecimal recaudacion;
	private BigDecimal retencion;
	private BigDecimal porcentajeEst;
	private BigDecimal pagoEst;
	private BigDecimal neto;
	private Long idUsuario;
	private Long idTicket;
	
	
	@Id
    @Column(name = "ID_LIQUIDACION", unique = true, nullable = false)
	public Long getIdLiquidacion() {
		return idLiquidacion;
	}
	
	public void setIdLiquidacion(Long idLiquidacion) {
		this.idLiquidacion = idLiquidacion;
	}
	
	@Column(name = "ID_MAQUINA", nullable = false)
	public Long getIdMaquina() {
		return idMaquina;
	}
	
	public void setIdMaquina(Long idMaquina) {
		this.idMaquina = idMaquina;
	}
	
	@Column(name = "ID_ESTABLECIMIENTO")
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
	
	@Column(name = "BRUTO", nullable = false)
	public BigDecimal getBruto() {
		return bruto;
	}
	
	public void setBruto(BigDecimal bruto) {
		this.bruto = bruto;
	}
	
	@Column(name = "FALLOS")
	public BigDecimal getFallos() {
		return fallos;
	}
	
	public void setFallos(BigDecimal fallos) {
		this.fallos = fallos;
	}
	
	@Column(name = "RECAUDACION", nullable = false)
	public BigDecimal getRecaudacion() {
		return recaudacion;
	}
	
	public void setRecaudacion(BigDecimal recaudacion) {
		this.recaudacion = recaudacion;
	}
	
	@Column(name = "RETENCION", nullable = false)
	public BigDecimal getRetencion() {
		return retencion;
	}
	
	public void setRetencion(BigDecimal retencion) {
		this.retencion = retencion;
	}
	
	@Column(name = "PORCENTAJEEST", nullable = false)
	public BigDecimal getPorcentajeEst() {
		return porcentajeEst;
	}
	
	public void setPorcentajeEst(BigDecimal porcentajeEst) {
		this.porcentajeEst = porcentajeEst;
	}
	
	@Column(name = "PAGOEST", nullable = false)
	public BigDecimal getPagoEst() {
		return pagoEst;
	}

	public void setPagoEst(BigDecimal pagoEst) {
		this.pagoEst = pagoEst;
	}
	
	@Column(name = "NETO", nullable = false)
	public BigDecimal getNeto() {
		return neto;
	}
	
	public void setNeto(BigDecimal neto) {
		this.neto = neto;
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
	
	
}