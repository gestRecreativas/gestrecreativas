package com.rms.recreativos.service;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class LiquidacionBean {

	private static final Logger logger = Logger.getLogger(LiquidacionBean.class);
	
	private BigDecimal bruto;
	private BigDecimal recPagoEmpresa;
	private BigDecimal recPagoEstablecimiento;
	private BigDecimal recaudacion;
	private BigDecimal retencion;
	private BigDecimal porcentajeEst;
	private BigDecimal fallos;
	private BigDecimal neto;
	
	public BigDecimal getBruto() {
		return bruto;
	}
	
	public void setBruto(BigDecimal bruto) {
		this.bruto = bruto;
	}
	
	public BigDecimal getRecPagoEmpresa() {
		return recPagoEmpresa;
	}
	
	public void setRecPagoEmpresa(BigDecimal recPagoEmpresa) {
		this.recPagoEmpresa = recPagoEmpresa;
	}
	
	public BigDecimal getRecPagoEstablecimiento() {
		return recPagoEstablecimiento;
	}
	
	public void setRecPagoEstablecimiento(BigDecimal recPagoEstablecimiento) {
		this.recPagoEstablecimiento = recPagoEstablecimiento;
	}
	
	public BigDecimal getRecaudacion() {
		return recaudacion;
	}
	
	public void setRecaudacion(BigDecimal recaudacion) {
		this.recaudacion = recaudacion;
	}
	
	public BigDecimal getRetencion() {
		return retencion;
	}
	
	public void setRetencion(BigDecimal retencion) {
		this.retencion = retencion;
	}
	
	public BigDecimal getPorcentajeEst() {
		return porcentajeEst;
	}
	
	public void setPorcentajeEst(BigDecimal porcentajeEst) {
		this.porcentajeEst = porcentajeEst;
	}
	
	public BigDecimal getFallos() {
		return fallos;
	}
	
	public void setFallos(BigDecimal fallos) {
		this.fallos = fallos;
	}
	
	public BigDecimal getNeto() {
		return neto;
	}
	
	public void setNeto(BigDecimal neto) {
		this.neto = neto;
	}
	
	public String toString() {
		return "{ \"Bruto\" : " + this.bruto != null? "\"" + this.bruto.toPlainString() + "\"":"\"\"" + "," +
				"\"RecPagoEmpresa\" : " + this.recPagoEmpresa != null? "\"" + this.recPagoEmpresa.toPlainString() + "\"":"\"\"" + "," +
				"\"RecPagoEstablecimiento\" : " + this.recPagoEstablecimiento != null? "\"" + this.recPagoEstablecimiento.toPlainString() + "\"":"\"\"" + "," +
				"\"Recaudacion\" : " + this.recaudacion != null? "\"" + this.recaudacion.toPlainString() + "\"":"\"\"" + "," +
				"\"Retencion\" : " + this.retencion != null? "\"" + this.retencion.toPlainString() + "\"":"\"\"" + "," +
				"\"PorcentajeEst\" : " + this.porcentajeEst != null? "\"" + this.porcentajeEst.toPlainString() + "\"":"\"\"" + "," +
				"\"Fallos\" : " + this.fallos != null? "\"" + this.fallos.toPlainString() + "\"":"\"\"" +"," +
				"\"Neto\" : " + this.neto != null? "\"" + this.neto.toPlainString() + "\"":"\"\"" +" }";
	}
	
	public JSONObject toJSON(){
		JSONObject jsonObj = null;
		try {
			jsonObj = new JSONObject(this.toString());
		} catch (JSONException e) {
			logger.error("ERROR LiquidacionBean.toJSON(): " + e.getMessage());
			e.printStackTrace();
		}
		return jsonObj;
	}
}