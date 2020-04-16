package com.rms.recreativos.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TIPO_MAQUINA", schema = "GESTRECREATIVAS")
public class TipoMaquina {

	private Long idTipoMaquina;
	private String tipo;
	private BigDecimal tasaMaquina;
	private BigDecimal tasaContador;
	
	@Id
    @Column(name = "ID_TIPO_MAQUINA", unique = true, nullable = false)
	public Long getIdTipoMaquina() {
		return idTipoMaquina;
	}
	
	public void setIdTipoMaquina(Long idTipoMaquina) {
		this.idTipoMaquina = idTipoMaquina;
	}
	
	@Column(name = "TIPO", length = 150, nullable = false)
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@Column(name = "TASA_MAQUINA", nullable = false)
	public BigDecimal getTasaMaquina() {
		return tasaMaquina;
	}
	
	public void setTasaMaquina(BigDecimal tasaMaquina) {
		this.tasaMaquina = tasaMaquina;
	}
	
	@Column(name = "TASA_CONTADOR", nullable = false)
	public BigDecimal getTasaContador() {
		return tasaContador;
	}
	
	public void setTasaContador(BigDecimal tasaContador) {
		this.tasaContador = tasaContador;
	}
}