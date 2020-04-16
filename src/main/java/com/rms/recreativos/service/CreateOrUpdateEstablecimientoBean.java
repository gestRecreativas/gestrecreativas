package com.rms.recreativos.service;

import java.util.List;

import com.rms.recreativos.entity.EmpresaMaquina;
import com.rms.recreativos.entity.Establecimiento;

public class CreateOrUpdateEstablecimientoBean {

	private Establecimiento establecimiento;
	private List<EmpresaMaquina> empresasMaquina;
	private boolean tieneMaquinas = false;

	public Establecimiento getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(Establecimiento establecimiento) {
		this.establecimiento = establecimiento;
	}

	public List<EmpresaMaquina> getEmpresasMaquina() {
		return empresasMaquina;
	}

	public void setEmpresasMaquina(List<EmpresaMaquina> empresasMaquina) {
		this.empresasMaquina = empresasMaquina;
	}

	public boolean getTieneMaquinas() {
		return tieneMaquinas;
	}

	public void setTieneMaquinas(boolean tieneMaquinas) {
		this.tieneMaquinas = tieneMaquinas;
	}
}