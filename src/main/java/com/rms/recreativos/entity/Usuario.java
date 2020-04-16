package com.rms.recreativos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO", schema = "GESTRECREATIVAS")
public class Usuario implements java.io.Serializable {

	private Long idUsuario;
	private Long idEmpresa;
	private String nif;
	private String nombreCompleto;
	private String login;
	private String password;
	private Long active;
	private Long admin;
	
	@Id
    @Column(name = "ID_USUARIO", unique = true, nullable = false)
	public Long getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	@Column(name = "ID_EMPRESA", nullable = false)
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
    @Column(name = "NIF", length = 10)
	public String getNif() {
		return nif;
	}
	
	public void setNif(String nif) {
		this.nif = nif;
	}
	
	@Column(name = "NOMBRE_COMPLETO", length = 100, nullable = false)
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	@Column(name = "LOGIN", length = 20, nullable = false)
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	@Column(name = "PASSWORD", length = 50, nullable = false)
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "ACTIVE", precision = 1, scale = 0)
	public Long getActive() {
		return active;
	}
	
	public void setActive(Long active) {
		this.active = active;
	}
	
	@Column(name = "ADMIN", precision = 1, scale = 0)
	public Long getAdmin() {
		return admin;
	}
	
	public void setAdmin(Long admin) {
		this.admin = admin;
	}
}