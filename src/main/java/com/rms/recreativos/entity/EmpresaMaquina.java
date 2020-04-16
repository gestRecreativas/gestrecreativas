package com.rms.recreativos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPRESA_MAQUINA", schema = "GESTRECREATIVAS")
public class EmpresaMaquina implements java.io.Serializable {

	private Long idEmpresaMaquina;
	private String cif;
	private String razonSocial;
	private String email;
	private Long idEmpresaGestion;
	
	@Id
    @Column(name = "ID_EMPRESA_MAQUINA", unique = true, nullable = false)
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

	
	
	@Column(name = "CIF", length = 50, nullable = false)
	public String getCif() {
		return cif;
	}
	
	public void setCif(String cif) {
		this.cif = cif;
	}
	
	@Column(name = "RAZON_SOCIAL", length = 150, nullable = false)
	public String getRazonSocial() {
		return razonSocial;
	}
	
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
	@Column(name = "EMAIL", length = 100)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}