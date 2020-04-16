package com.rms.recreativos.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ESTABLECIMIENTO", schema = "GESTRECREATIVAS")
public class Establecimiento implements java.io.Serializable {

	private Long idEstablecimiento;
	private String codigoEstablecimiento;
	private String nombre;
	private String email;
	private BigDecimal bote;
	private Long idEmpresaMaquina;
	private Long idEmpresaGestion;
	
	@Id
    @Column(name = "ID_ESTABLECIMIENTO", unique = true, nullable = false)
	public Long getIdEstablecimiento() {
		return idEstablecimiento;
	}
	
	public void setIdEstablecimiento(Long idEstablecimiento) {
		this.idEstablecimiento = idEstablecimiento;
	}
	
	@Column(name = "CODIGO_ESTABLECIMIENTO", length = 50, nullable = false)
	public String getCodigoEstablecimiento() {
		return codigoEstablecimiento;
	}
	
	public void setCodigoEstablecimiento(String codigoEstablecimiento) {
		this.codigoEstablecimiento = codigoEstablecimiento;
	}
	
	@Column(name = "NOMBRE", length = 255, nullable = false)
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "EMAIL", length = 100, nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "BOTE", nullable = false)
	public BigDecimal getBote() {
		return bote;
	}

	public void setBote(BigDecimal bote) {
		this.bote = bote;
	}
	
	@Column(name = "ID_EMPRESA_MAQUINA", nullable = false)
	public Long getIdEmpresaMaquina() {
		return idEmpresaMaquina;
	}
	
	public void setIdEmpresaMaquina(Long idEmpresaMaquina) {
		this.idEmpresaMaquina = idEmpresaMaquina;
	}
	
	@Column(name = "ID_EMPRESA_GESTION", nullable = false)
	public Long getIdEmpresaGestion() {
		return idEmpresaGestion;
	}
	
	public void setIdEmpresaGestion(Long idEmpresaGestion) {
		this.idEmpresaGestion = idEmpresaGestion;
	}
}