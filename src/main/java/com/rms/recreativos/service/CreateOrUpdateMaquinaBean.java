package com.rms.recreativos.service;

import java.util.List;

import com.rms.recreativos.entity.EmpresaMaquina;
import com.rms.recreativos.entity.Establecimiento;
import com.rms.recreativos.entity.Maquina;
import com.rms.recreativos.entity.TipoMaquina;

public class CreateOrUpdateMaquinaBean {

	private Maquina maquina;
	private List<Establecimiento> establecimientos;
	private List<TipoMaquina> tiposMaquina;
	private List<EmpresaMaquina> empresasMaquina;
	
	public Maquina getMaquina() {
		return maquina;
	}
	
	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}
	
	public List<Establecimiento> getEstablecimientos() {
		return establecimientos;
	}
	
	public void setEstablecimientos(List<Establecimiento> establecimientos) {
		this.establecimientos = establecimientos;
	}
	
	public List<TipoMaquina> getTiposMaquina() {
		return tiposMaquina;
	}
	
	public void setTiposMaquina(List<TipoMaquina> tiposMaquina) {
		this.tiposMaquina = tiposMaquina;
	}

	public List<EmpresaMaquina> getEmpresasMaquina() {
		return empresasMaquina;
	}

	public void setEmpresasMaquina(List<EmpresaMaquina> empresasMaquina) {
		this.empresasMaquina = empresasMaquina;
	}
}