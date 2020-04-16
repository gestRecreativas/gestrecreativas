package com.rms.recreativos.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rms.recreativos.dao.RecreativosDAO;
import com.rms.recreativos.entity.Contador;
import com.rms.recreativos.entity.Credito;
import com.rms.recreativos.entity.EmpresaGestion;
import com.rms.recreativos.entity.EmpresaMaquina;
import com.rms.recreativos.entity.Establecimiento;
import com.rms.recreativos.entity.Liquidacion;
import com.rms.recreativos.entity.Maquina;
import com.rms.recreativos.entity.Ticket;
import com.rms.recreativos.entity.TipoMaquina;
import com.rms.recreativos.entity.Usuario;
import com.rms.recreativos.util.DateUtil;
import com.rms.recreativos.util.ListUtil;
import com.rms.recreativos.util.StringUtil;

public class RecreativosService {

	private static final Logger logger = Logger.getLogger(RecreativosService.class);
	
	@Autowired
	private RecreativosDAO RecreativosDAO;
	
	public JSONObject getLiquidacionMaquina(Long idMaquina, Long idUsuario, boolean saveLiquidacion){
		Liquidacion liquidacion = null;
		Liquidacion liquidacionActual = RecreativosDAO.getLiquidacionActual(idMaquina);
		logger.debug("liquidacionActual: " + liquidacionActual);
		if (saveLiquidacion){
			liquidacion = liquidacionActual;
		} else {
			Contador contadorAnt = RecreativosDAO.getLastContador(idMaquina);
			logger.debug("contadorAnt: " + contadorAnt);
			Contador contadorActual = RecreativosDAO.getContadorActual(idMaquina);
			logger.debug("contadorActual: " + contadorActual);
			TipoMaquina tipoMaquina = RecreativosDAO.getTipoMaquina(idMaquina);
			logger.debug("tipoMaquina: " + tipoMaquina);
			liquidacion = calculaLiquidacion(liquidacionActual, contadorAnt, contadorActual, tipoMaquina, idUsuario);
		}
		logger.debug("liquidacion: " + liquidacion);
		JSONObject jsonObject = toJSON(getJSONStringLiquidacion(liquidacion));
		BigDecimal retencionAcumuladaAnio = RecreativosDAO.getRetencionAcumuladaAnio(idMaquina);
		try {
			if (retencionAcumuladaAnio != null){
				jsonObject.put("retencionAcumuladaAnio", retencionAcumuladaAnio.setScale(2).toPlainString());
			} else {
				jsonObject.put("retencionAcumuladaAnio", "0,00");
			}
		} catch (Exception e) {
			logger.error("ERROR getLiquidacionMaquina: " + e.getMessage());
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	public Usuario getUsuarioByLoginPassword(String usuario, String password){
		return RecreativosDAO.getUsuarioByLoginPassword(usuario, password);
	}
	
	public MaquinaBean getMaquinaBean(Long idMaquina){
		Maquina maquina = RecreativosDAO.getMaquinaById(idMaquina);
		Establecimiento establecimiento = RecreativosDAO.getEstablecimientoById(maquina.getIdEstablecimiento());
		MaquinaBean maquinaBean = new MaquinaBean(maquina, establecimiento);
		Contador contadorAnt = RecreativosDAO.getLastContador(idMaquina);
		if (contadorAnt != null){
			maquinaBean.setContadorAnt(contadorAnt);
		}
		Contador contadorActual = RecreativosDAO.getContadorActual(idMaquina);
		if (contadorActual != null){
			maquinaBean.setContadorActual(contadorActual);
		}
		Liquidacion liquidacionActual = RecreativosDAO.getLiquidacionActual(idMaquina);
		maquinaBean.setLiquidacionActual(liquidacionActual);
		BigDecimal porcentajeEstablecimiento = maquina.getPorcentajeEstablecimiento();
		maquinaBean.setPorcentajeEstablecimiento(porcentajeEstablecimiento);
		BigDecimal retencionAcumuladaAnio = RecreativosDAO.getRetencionAcumuladaAnio(idMaquina);
		maquinaBean.setRetencionAcumuladaAnio(retencionAcumuladaAnio);
		Ticket ticketActual = RecreativosDAO.getTicketActual(establecimiento.getIdEstablecimiento());
		maquinaBean.setHayTicketActual(ticketActual != null);
		return maquinaBean;
	}
	
	public Maquina getMaquinaById(Long idMaquina) {
		return RecreativosDAO.getMaquinaById(idMaquina);
	}
	
	public List<TipoMaquina> getTiposMaquina(){
		return RecreativosDAO.getTiposMaquina();
	}
	
	public List<EmpresaMaquina> getEmpresasMaquina(){
		return RecreativosDAO.getEmpresasMaquina();
	}
	
	public Establecimiento getEstablecimientoById(Long idEstablecimiento){
		return RecreativosDAO.getEstablecimientoById(idEstablecimiento);
	}
	
	public Credito getCreditoPendiente(Long idEstablecimiento){
		return RecreativosDAO.getCreditoPendiente(idEstablecimiento);
	}
	
	public Ticket getTicketActual(Long idEstablecimiento){
		return RecreativosDAO.getTicketActual(idEstablecimiento);
	}
	
	public Ticket getTicketById(Long idTicket){
		return RecreativosDAO.getTicketById(idTicket);
	}
	
	public List<Maquina> getMaquinasByIdEstablecimiento(Long idEstablecimiento){
		return RecreativosDAO.getMaquinasByIdEstablecimiento(idEstablecimiento);
	}
	
	public Contador getContadorByTicketAndMaquina(Long idTicket, Long idMaquina){
		return RecreativosDAO.getContadorByTicketAndMaquina(idTicket, idMaquina);
	}
	
	public Liquidacion getLiquidacionByTicketAndMaquina(Long idTicket, Long idMaquina){
		return RecreativosDAO.getLiquidacionByTicketAndMaquina(idTicket, idMaquina);
	}
	
	public EmpresaMaquina getEmpresaMaquinaById(Long idEmpresaMaquina){
		return RecreativosDAO.getEmpresaMaquinaById(idEmpresaMaquina);
	}
	
	public List<Establecimiento> getEstablecimientosByIdEmpresaMaquina(Long idEmpresaMaquina){
		return RecreativosDAO.getEstablecimientosByIdEmpresaMaquina(idEmpresaMaquina);
	}
	
	public boolean getTieneMaquinas(Long idEstablecimiento) {
		return RecreativosDAO.getTieneMaquinas(idEstablecimiento);
	}
	
	public Contador getContadorAnteriorByContador(Contador contador){
		if (contador != null){
			Long idMaquina = contador.getIdMaquina();
			Date fecha = contador.getFecha();
			return RecreativosDAO.getContadorAnterior(idMaquina, fecha);
		}
		return null;
	}
	
	private Liquidacion calculaLiquidacion(Liquidacion liquidacionActual, Contador contadorAnt, 
			Contador contadorActual, TipoMaquina tipoMaquina, Long idUsuario){
		Liquidacion liquidacion = null;
		if (liquidacionActual != null){
			liquidacion = liquidacionActual;
		} else {
			liquidacion = new Liquidacion();
			liquidacion.setIdMaquina(contadorActual.getIdMaquina());
			liquidacion.setFecha(DateUtil.getNow());
		}
		
		if (contadorAnt != null && contadorActual != null){
			Maquina maquina = RecreativosDAO.getMaquinaById(contadorActual.getIdMaquina());
			Establecimiento establecimiento = RecreativosDAO.getEstablecimientoById(maquina.getIdEstablecimiento());
			BigDecimal bote = establecimiento.getBote();
			BigInteger entradaAnt = contadorAnt.getEntrada();
			logger.debug("entradaAnt: " + entradaAnt);
			BigInteger entradaActual = contadorActual.getEntrada();
			logger.debug("entradaActual: " + entradaActual);
			BigInteger difEntrada = entradaActual.subtract(entradaAnt);
			logger.debug("difEntrada: " + difEntrada);
			BigInteger salidaAnt = contadorAnt.getSalida();
			logger.debug("salidaAnt: " + salidaAnt);
			BigInteger salidaActual = contadorActual.getSalida();
			logger.debug("salidaActual: " + salidaActual);
			BigInteger difSalida = salidaActual.subtract(salidaAnt);
			logger.debug("difSalida: " + difSalida);
			BigInteger difEntradaSalida = difEntrada.subtract(difSalida);
			logger.debug("difEntradaSalida: " + difEntradaSalida);
			BigDecimal bruto = new BigDecimal(difEntradaSalida).multiply(tipoMaquina.getTasaContador()).setScale(2);
			BigDecimal recaudacion = bruto;
			logger.debug("recaudacion: " + recaudacion);
			liquidacion.setRecaudacion(recaudacion);
			BigDecimal fallos = BigDecimal.ZERO.setScale(2);
			if (bote != null){
				bruto = bruto.subtract(bote);
				fallos = bote;
			}
			logger.debug("bruto: " + bruto);
			liquidacion.setBruto(bruto);
			logger.debug("fallos: " + fallos);
			liquidacion.setFallos(fallos);
			BigDecimal retencion = tipoMaquina.getTasaMaquina().setScale(2);
			logger.debug("retencion: " + retencion);
			liquidacion.setRetencion(retencion);
			BigDecimal importeTotalSinRetencion = bruto.subtract(retencion).setScale(2);
			logger.debug("importeTotalSinRetencion: " + importeTotalSinRetencion);
			BigDecimal porcentajeEstablecimiento = maquina.getPorcentajeEstablecimiento().setScale(2);
			logger.debug("porcentajeEstablecimiento: " + porcentajeEstablecimiento);
			liquidacion.setPorcentajeEst(porcentajeEstablecimiento);
			BigDecimal pagoEst = importeTotalSinRetencion.multiply(porcentajeEstablecimiento).divide(new BigDecimal(100)).setScale(2);
			logger.debug("pagoEst: " + pagoEst);
			liquidacion.setPagoEst(pagoEst);
			BigDecimal neto = importeTotalSinRetencion.subtract(pagoEst).setScale(2);
			logger.debug("neto: " + neto);
			liquidacion.setNeto(neto);
			liquidacion.setIdUsuario(idUsuario);
			liquidacion.setIdEstablecimiento(establecimiento.getIdEstablecimiento());
			liquidacion.setIdEmpresaMaquina(maquina.getIdEmpresaMaquina());
			if (liquidacion.getIdLiquidacion() == null){
				liquidacion = RecreativosDAO.createLiquidacion(liquidacion);
			} else {
				liquidacion = RecreativosDAO.updateLiquidacion(liquidacion);
			}
		}
		return liquidacion;
	}
	
	private String getJSONStringLiquidacion(Liquidacion liquidacion){
		String jsonStr = "{}";
		try {
			ObjectMapper objMapper = new ObjectMapper();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			objMapper.setDateFormat(df);
			jsonStr = objMapper.writeValueAsString(liquidacion);
		} catch (JsonProcessingException e) {
			logger.error("ERROR getJSONStringLiquidacion: " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;
	}
	
	private JSONObject toJSON(String jsonString){
		JSONObject jsonObj = null;
		try {
			jsonObj = new JSONObject(jsonString);
		} catch (JSONException e) {
			logger.error("ERROR LiquidacionBean.toJSON(): " + e.getMessage());
			e.printStackTrace();
		}
		return jsonObj;
	}
	
	public TicketBean createOrUpdateTicket(EmpresaMaquina empresaMaquina, Establecimiento establecimiento, 
			List<Maquina> maquinas, Credito credito, Long idUsuario, Long idTicket){
		TicketBean ticketBean = new TicketBean(empresaMaquina, establecimiento, maquinas, credito);
		Iterator<Maquina> itMaquinas = maquinas.iterator();
		List<Contador> contadores = new ArrayList<Contador>();
		List<Liquidacion> liquidaciones = new ArrayList<Liquidacion>();
		Maquina maquina = null;
		Contador contador = null;
		Contador contadorAnterior = null;
		Liquidacion liquidacion = null;
		InfoTicketMaquinaBean infoTicketMaquinaBean = null;
		BigDecimal total = BigDecimal.ZERO.setScale(2);
		BigDecimal retencion = BigDecimal.ZERO.setScale(2);
		BigDecimal establecimientoTicket = BigDecimal.ZERO.setScale(2);
		BigDecimal empresa = BigDecimal.ZERO.setScale(2);
		BigDecimal cuotaCredito = null;
		Ticket ticket = null;
		if (idTicket != null){
			ticket = RecreativosDAO.getTicketById(idTicket);
		} else {
			ticket = new Ticket();
			ticket.setIdEstablecimiento(establecimiento.getIdEstablecimiento());
			if (empresaMaquina != null){
				ticket.setIdEmpresaMaquina(empresaMaquina.getIdEmpresaMaquina());
			}
			ticket.setFecha(DateUtil.getNow());				
			ticket.setHora(DateUtil.getStringFromDate(new Date(), "HH:mm"));			
		}
		ticket.setIdUsuario(idUsuario);
		int completo = 1;
		Long idEmpresaMaquina = null;
		while (itMaquinas.hasNext()){
			infoTicketMaquinaBean = null;
			maquina = itMaquinas.next();
			if (idEmpresaMaquina == null){
				idEmpresaMaquina = maquina.getIdEmpresaMaquina();
			}
			if (idTicket != null){
				contador = RecreativosDAO.getContadorByTicketAndMaquina(idTicket, maquina.getIdMaquina());
				if (contador == null) {
					contador = RecreativosDAO.getContadorByFechaAndMaquina(ticket.getFecha(), maquina.getIdMaquina());
				}
			} else {
				contador = RecreativosDAO.getContadorActual(maquina.getIdMaquina());
			}
			if (contador != null){
				contadores.add(contador);
			}
			contadorAnterior = getContadorAnteriorByContador(contador);
			if (idTicket != null){
				liquidacion = RecreativosDAO.getLiquidacionByTicketAndMaquina(idTicket, maquina.getIdMaquina());
				if (liquidacion == null) {
					liquidacion = RecreativosDAO.getLiquidacionByFechaAndMaquina(ticket.getFecha(), maquina.getIdMaquina());
				}
			} else {
				liquidacion = RecreativosDAO.getLiquidacionActual(maquina.getIdMaquina());
			}
			if (liquidacion != null){
				liquidaciones.add(liquidacion);
			}
			infoTicketMaquinaBean = new InfoTicketMaquinaBean(contador, contadorAnterior, liquidacion);
			ticketBean.putInfoMaquina(maquina.getIdMaquina(), infoTicketMaquinaBean);
			if (contador == null || liquidacion == null){
				completo = 0;
			}
			if (liquidacion != null){
				total = total.add(liquidacion.getBruto()).setScale(2);
				retencion = retencion.add(liquidacion.getRetencion()).setScale(2);
				establecimientoTicket = establecimientoTicket.add(liquidacion.getPagoEst()).setScale(2);
				empresa = empresa.add(liquidacion.getNeto()).setScale(2);
			}
		}
		if (ticket.getIdEmpresaMaquina() == null && idEmpresaMaquina != null){
			ticket.setIdEmpresaMaquina(idEmpresaMaquina);
		}
		
		ticket.setCompleto(new Long(completo));
		ticket.setTotal(total);
		ticket.setRetencion(retencion);
		if (credito != null){
			BigDecimal importePendiente = null;
			ticket.setIdCredito(credito.getIdCredito());	
			
			importePendiente = credito.getImportePendiente();
			if (importePendiente == null){
				importePendiente=BigDecimal.ZERO;
			}
			
			BigDecimal cuota =credito.getCuota();
			if (cuota == null){
				cuota=BigDecimal.ZERO;
			}
			
			BigDecimal importePendienteActual=credito.getCreditoInicialActual();
			if (importePendienteActual == null ){
				importePendienteActual=BigDecimal.ZERO;
			}
			
			
			if(DateUtil.esHoy(credito.getFechaCobro()) >= 0 ){
				importePendiente=importePendienteActual;				
			}else{
				credito.setCreditoInicialActual(importePendiente);
				credito.setFechaCobro( new Date());
			}
			if(establecimientoTicket.compareTo(BigDecimal.ZERO)<0){
				cuota =BigDecimal.ZERO;
			}else if (establecimientoTicket.compareTo(cuota) < 0){
				cuota = establecimientoTicket.setScale(2);
			}
			establecimientoTicket = establecimientoTicket.subtract(cuota).setScale(2);			
			importePendiente=importePendiente.setScale(2);
			cuota=cuota.setScale(2);
			
			ticket.setCreditoPendienteInicial(importePendiente);
			
			importePendiente = importePendiente.subtract(cuota).setScale(2);
			empresa = empresa.add(cuota).setScale(2);
			credito.setImportePendiente(importePendiente);
			credito = RecreativosDAO.updateCredito(credito);
			ticketBean.setCredito(credito);
			cuotaCredito = cuota;
			
		}
		BigDecimal neto = empresa.add(retencion).setScale(2);
		ticket.setEstablecimiento(establecimientoTicket);
		ticket.setEmpresa(empresa);
		ticket.setNeto(neto);
		ticket.setCuotaCredito(cuotaCredito);
		if (idTicket != null){
			ticket = RecreativosDAO.updateTicket(ticket);
		} else {
			if (!ListUtil.nullOrEmptyList(contadores) && !ListUtil.nullOrEmptyList(liquidaciones)){
				ticket = RecreativosDAO.createTicket(ticket);
			}
		}
		if (!ListUtil.nullOrEmptyList(contadores)){
			RecreativosDAO.updateTicketContadores(ticket.getIdTicket(), contadores);
		}
		if (!ListUtil.nullOrEmptyList(liquidaciones)){
			RecreativosDAO.updateTicketLiquidaciones(ticket.getIdTicket(), liquidaciones);
		}
		ticketBean.setTicket(ticket);
		return ticketBean;
	}
	
	public EmpresaGestion getEmpresaGestionByIdUsuario(Long idUsuario){
		return RecreativosDAO.getEmpresaGestionByIdUsuario(idUsuario);
	}
	
	public List<Ticket> getTicketsHistorico(Long idEstablecimiento, Long idUsuario, String fechaInicio, String fechaFin){
		Date inicio = null;
		Date fin = null;
		if (!StringUtil.isNullOrEmptyOrBlankString(fechaInicio)){
			inicio = DateUtil.getDateFromString(fechaInicio, "dd-MM-yyyy");
		}
		if (!StringUtil.isNullOrEmptyOrBlankString(fechaFin)){
			fin = DateUtil.getDateFromString(fechaFin, "dd-MM-yyyy");
		}
		return RecreativosDAO.getTicketsHistorico(idEstablecimiento, idUsuario, inicio, fin);
	}
	
	public List<RecaudacionBean> getRecaudaciones(Long idUsuario, Date fecha){
		return RecreativosDAO.getRecaudaciones(idUsuario, fecha);
	}
}