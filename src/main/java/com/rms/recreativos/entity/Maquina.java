package com.rms.recreativos.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MAQUINA", schema = "GESTRECREATIVAS")
public class Maquina implements java.io.Serializable {

	private Long idMaquina;
	private String codigoMaquina;
	private String nombre;
	private BigDecimal porcentajeEstablecimiento;
	private Long idEmpresaMaquina;
	private Long idTipoMaquina;
	private Long idEmpresaGestion;
	private Long idEstablecimiento;
	private BigDecimal cargas;
	
	@Id
    @Column(name = "ID_MAQUINA", unique = true, nullable = false)
	public Long getIdMaquina() {
		return idMaquina;
	}
	
	public void setIdMaquina(Long idMaquina) {
		this.idMaquina = idMaquina;
	}
	
	@Column(name = "CODIGO_MAQUINA", length = 50, nullable = false)
	public String getCodigoMaquina() {
		return codigoMaquina;
	}
	
	public void setCodigoMaquina(String codigoMaquina) {
		this.codigoMaquina = codigoMaquina;
	}
	
	@Column(name = "NOMBRE", length = 255, nullable = false)
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(name = "PORCENTAJE_ESTABLECIMIENTO", nullable = false)
	public BigDecimal getPorcentajeEstablecimiento() {
		return porcentajeEstablecimiento;
	}

	public void setPorcentajeEstablecimiento(BigDecimal porcentajeEstablecimiento) {
		this.porcentajeEstablecimiento = porcentajeEstablecimiento;
	}
	
	@Column(name = "ID_EMPRESA_MAQUINA", nullable = false)
	public Long getIdEmpresaMaquina() {
		return idEmpresaMaquina;
	}
	
	public void setIdEmpresaMaquina(Long idEmpresaMaquina) {
		this.idEmpresaMaquina = idEmpresaMaquina;
	}
	
	@Column(name = "ID_TIPO_MAQUINA", nullable = false)
	public Long getIdTipoMaquina() {
		return idTipoMaquina;
	}
	
	public void setIdTipoMaquina(Long idTipoMaquina) {
		this.idTipoMaquina = idTipoMaquina;
	}
	
	@Column(name = "ID_EMPRESA_GESTION", nullable = false)
	public Long getIdEmpresaGestion() {
		return idEmpresaGestion;
	}
	
	public void setIdEmpresaGestion(Long idEmpresaGestion) {
		this.idEmpresaGestion = idEmpresaGestion;
	}
	
	@Column(name = "ID_ESTABLECIMIENTO", nullable = false)
	public Long getIdEstablecimiento() {
		return idEstablecimiento;
	}
	
	public void setIdEstablecimiento(Long idEstablecimiento) {
		this.idEstablecimiento = idEstablecimiento;
	}

	@Column(name = "CARGAS", nullable = false)
	public BigDecimal getCargas() {
		return cargas;
	}

	public void setCargas(BigDecimal cargas) {
		this.cargas = cargas;
	}
}