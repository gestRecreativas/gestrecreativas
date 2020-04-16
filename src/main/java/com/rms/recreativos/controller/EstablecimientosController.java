package com.rms.recreativos.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rms.recreativos.entity.Contador;
import com.rms.recreativos.entity.Credito;
import com.rms.recreativos.entity.EmpresaMaquina;
import com.rms.recreativos.entity.Establecimiento;
import com.rms.recreativos.entity.Liquidacion;
import com.rms.recreativos.entity.Maquina;
import com.rms.recreativos.entity.Ticket;
import com.rms.recreativos.entity.TipoMaquina;
import com.rms.recreativos.entity.Usuario;
import com.rms.recreativos.mail.MailUtil;
import com.rms.recreativos.service.CreateOrUpdateEstablecimientoBean;
import com.rms.recreativos.service.CreateOrUpdateMaquinaBean;
import com.rms.recreativos.service.EstablecimientoBean;
import com.rms.recreativos.service.HistoricoBean;
import com.rms.recreativos.service.InfoTicketMaquinaBean;
import com.rms.recreativos.service.MaquinaBean;
import com.rms.recreativos.service.PropertiesService;
import com.rms.recreativos.service.RecaudacionBean;
import com.rms.recreativos.service.RecaudacionesBean;
import com.rms.recreativos.service.RecreativosService;
import com.rms.recreativos.service.TicketBean;
import com.rms.recreativos.util.DateUtil;
import com.rms.recreativos.util.ListUtil;
import com.rms.recreativos.util.SessionUtil;
import com.rms.recreativos.util.StringUtil;

@Controller
public class EstablecimientosController {

	private static final Logger logger = Logger.getLogger(EstablecimientosController.class);
	
	@Autowired
	private RecreativosService rs;
	
	@Autowired
	MailUtil mailUtil;
	
	@Autowired
	PropertiesService propertiesService;
	
	@RequestMapping(value={"/establecimientos.html"})
	public String webEstablecimientos(HttpServletRequest request, ModelMap model){
		return "establecimientos";
	}
	
	@RequestMapping(value={"/establecimiento.html"})
	public ModelAndView webEstablecimiento(HttpServletRequest request, ModelMap model,
			@RequestParam(value="idEstablecimiento") Long idEstablecimiento){
		Establecimiento establecimiento = rs.getEstablecimientoById(idEstablecimiento);
		Credito credito = rs.getCreditoPendiente(idEstablecimiento);
		EstablecimientoBean establecimientoBean = new EstablecimientoBean(establecimiento, credito);
		return new ModelAndView("establecimiento", "establecimientoBean", establecimientoBean);
	}
	
	@RequestMapping(value={"/maquina.html"})
	public ModelAndView webMaquina(HttpServletRequest request, ModelMap model,
			@RequestParam(value="idMaquina") Long idMaquina){
		MaquinaBean maquinaBean = rs.getMaquinaBean(idMaquina);
		return new ModelAndView("maquina", "maquinaBean", maquinaBean);
	}
	
	@RequestMapping(value={"/credito.html"})
	public ModelAndView webCredito(HttpServletRequest request, ModelMap model,
			@RequestParam(value="idEstablecimiento") Long idEstablecimiento){
		Establecimiento establecimiento = rs.getEstablecimientoById(idEstablecimiento);
		Credito credito = rs.getCreditoPendiente(idEstablecimiento);
		EstablecimientoBean establecimientoBean = new EstablecimientoBean(establecimiento, credito);
		return new ModelAndView("credito", "establecimientoBean", establecimientoBean);
	}
	
	@RequestMapping(value={"/ticket.html"})
	public ModelAndView webTicket(HttpServletRequest request, ModelMap model,
			@RequestParam(value="idEstablecimiento") Long idEstablecimiento,
			@RequestParam(value="idUsuario") Long idUsuario,
			@RequestParam(value="idTicket", required=false) Long idTicket,
			@RequestParam(value="fechaInicio", required=false) String fechaInicio,
			@RequestParam(value="fechaFin", required=false) String fechaFin,
			@RequestParam(value="fechaRecaudacion", required=false) String fechaRecaudacion){
		Establecimiento establecimiento = rs.getEstablecimientoById(idEstablecimiento);
		List<Maquina> maquinas = rs.getMaquinasByIdEstablecimiento(idEstablecimiento);
		EmpresaMaquina empresaMaquina = null;
		if (!ListUtil.nullOrEmptyList(maquinas)){
			empresaMaquina = rs.getEmpresaMaquinaById(((Maquina) maquinas.get(0)).getIdEmpresaMaquina());
		}
		Credito credito = rs.getCreditoPendiente(idEstablecimiento);
		TicketBean ticketBean = new TicketBean(empresaMaquina, establecimiento, maquinas, credito);
		Iterator<Maquina> itMaquinas = maquinas.iterator();
		Maquina maquina = null;
		Contador contador = null;
		Contador contadorAnterior = null;
		Liquidacion liquidacion = null;
		InfoTicketMaquinaBean infoTicketMaquinaBean = null;
		if (idTicket == null){
			Ticket ticket = rs.getTicketActual(idEstablecimiento);
			if (ticket != null){
				ticketBean = rs.createOrUpdateTicket(empresaMaquina, establecimiento, maquinas, credito, idUsuario, ticket.getIdTicket());
			} else {
				ticketBean = rs.createOrUpdateTicket(empresaMaquina, establecimiento, maquinas, credito, idUsuario, null);
			}
		} else {
			Ticket ticket = rs.getTicketById(idTicket);
			Ticket ticketActual = rs.getTicketActual(idEstablecimiento);
			if ((ticketActual == null || !ticketActual.getIdTicket().equals(ticket.getIdTicket()))
					&& ticket.getCompleto() != null && ticket.getCompleto().intValue() == 1){
				ticketBean.setTicket(ticket);
				while (itMaquinas.hasNext()){
					infoTicketMaquinaBean = null;
					maquina = itMaquinas.next();
					contador = rs.getContadorByTicketAndMaquina(ticket.getIdTicket(), maquina.getIdMaquina());
					contadorAnterior = rs.getContadorAnteriorByContador(contador);
					liquidacion = rs.getLiquidacionByTicketAndMaquina(ticket.getIdTicket(), maquina.getIdMaquina());
					infoTicketMaquinaBean = new InfoTicketMaquinaBean(contador, contadorAnterior, liquidacion);
					ticketBean.putInfoMaquina(maquina.getIdMaquina(), infoTicketMaquinaBean);
				}
			} else {
				ticketBean = rs.createOrUpdateTicket(empresaMaquina, establecimiento, maquinas, credito, idUsuario, ticket.getIdTicket());
			}
			ticketBean.setFechaInicioFiltro(fechaInicio);
			ticketBean.setFechaFinFiltro(fechaFin);
			ticketBean.setFechaRecaudacion(fechaRecaudacion);
		}
		String nombreFichero = establecimiento.getNombre().toUpperCase() + "_" + DateUtil.getStringFromDate(ticketBean.getTicket().getFecha(), "ddMMyyyy") + ".txt";
		ticketBean.setFicheroTxt(nombreFichero);
		generaFicheroTxt(ticketBean, nombreFichero);
		return new ModelAndView("ticket", "ticketBean", ticketBean);
	}
	
	@RequestMapping(value={"/historico.html"})
	public ModelAndView webHistoricoTicket(HttpServletRequest request, ModelMap model,
			@RequestParam(value="idEstablecimiento") Long idEstablecimiento,
			@RequestParam(value="idUsuario") Long idUsuario,
			@RequestParam(value="fechaInicio", required=false) String fechaInicio,
			@RequestParam(value="fechaFin", required=false) String fechaFin){
		Establecimiento establecimiento = rs.getEstablecimientoById(idEstablecimiento);
		List<Ticket> tickets = rs.getTicketsHistorico(idEstablecimiento, idUsuario, fechaInicio, fechaFin);
		HistoricoBean historicoBean = new HistoricoBean(establecimiento, tickets);
		historicoBean.setFechaInicioFiltro(fechaInicio);
		historicoBean.setFechaFinFiltro(fechaFin);
		return new ModelAndView("historico", "historicoBean", historicoBean);
	}
	
	@RequestMapping(value={"/printTicket.html"})
	public ModelAndView webPrintTicket(HttpServletRequest request, ModelMap model,
			@RequestParam(value="idEstablecimiento") String idEstablecimiento,
			@RequestParam(value="establecimiento") String establecimiento,
			@RequestParam(value="nombreFichero") String nombreFichero){
		File carpetaEstablecimiento = new File(propertiesService.getRutaFicherosTicket() + File.separator +
				idEstablecimiento + "_" + establecimiento);
		File ficheroTxt = new File(carpetaEstablecimiento, nombreFichero);
		return new ModelAndView("printTicket", "ficheroTxt", ficheroTxt);
	}
	
	@RequestMapping(value={"/recaudaciones.html"})
	public ModelAndView webRecaudaciones(HttpServletRequest request, ModelMap model,
			@RequestParam(value="fecha", required=false) String fecha){
		Usuario usuario = SessionUtil.getUsuario(request.getSession());
		Date fechaFiltro = DateUtil.getNow();
		if (!StringUtil.isNullOrEmptyOrBlankString(fecha)){
			fechaFiltro = DateUtil.getDateFromString(fecha, "dd-MM-yyyy");
		}
		List<RecaudacionBean> recaudaciones = rs.getRecaudaciones(usuario.getIdUsuario(), fechaFiltro);
		RecaudacionesBean recaudacionesBean = new RecaudacionesBean(recaudaciones, fechaFiltro);
		return new ModelAndView("recaudaciones", "recaudacionesBean", recaudacionesBean);
	}
	
	private void generaFicheroTxt(TicketBean ticketBean, String nombreFichero){
		File carpetaRaiz = new File(propertiesService.getRutaFicherosTicket());
		if (!carpetaRaiz.exists() || !carpetaRaiz.isDirectory()){
			carpetaRaiz.mkdir();
		}
		File carpetaEstablecimiento = new File(propertiesService.getRutaFicherosTicket() + File.separator +
				ticketBean.getEstablecimiento().getIdEstablecimiento().toString() + "_" + ticketBean.getEstablecimiento().getNombre().toUpperCase());
		if (!carpetaEstablecimiento.exists() || !carpetaEstablecimiento.isDirectory()){
			carpetaEstablecimiento.mkdir();
		}
		File fichero = new File(carpetaEstablecimiento, nombreFichero);
		if (fichero.exists()){
			fichero.delete();
		}
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fichero = new File(carpetaEstablecimiento, nombreFichero);
			fw = new FileWriter(fichero);
			pw = new PrintWriter(fw);
			pw.print(getFileTxtContent(ticketBean).toString());
		} catch (Exception e) {
			logger.error("ERROR generando fichero: " + e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (Exception e) {
				logger.error("ERROR generando fichero: " + e.getMessage(), e);
				e.printStackTrace();
			}
		}
	}
	
	private StringBuffer getFileTxtContent(TicketBean ticketBean){
		EmpresaMaquina empresaMaquina = ticketBean.getEmpresaMaquina();
		Establecimiento establecimiento = ticketBean.getEstablecimiento();
		List<Maquina> maquinas = ticketBean.getMaquinas();
		LinkedHashMap<Long, InfoTicketMaquinaBean> infoMaquinas = ticketBean.getInfoMaquinas();
		Ticket ticket = ticketBean.getTicket();
		Credito credito = ticketBean.getCredito();
		final String cambioLinea = "\n";
		final String separador = "  ";
		final String separadorSeccion = "\n";
		StringBuffer sb = new StringBuffer();
		sb.append(empresaMaquina.getRazonSocial().toUpperCase() + cambioLinea);
		sb.append(empresaMaquina.getCif() + cambioLinea);
		sb.append(separadorSeccion);
		sb.append(establecimiento.getNombre().toUpperCase() + separador + establecimiento.getCodigoEstablecimiento() + cambioLinea);
		sb.append(separadorSeccion);
		Iterator<Maquina> itMaquinas = maquinas.iterator();
		Maquina maquina = null;
		Contador contador = null;
		Contador contadorAnterior = null;
		Liquidacion liquidacion = null;
		InfoTicketMaquinaBean infoTicketMaquinaBean = null;
		BigInteger entradaAnt = null;
		BigInteger entradaActual = null;
		BigInteger difEntrada = null;
		BigInteger salidaAnt = null;
		BigInteger salidaActual = null;
		BigInteger difSalida = null;
		BigDecimal total = null;
		BigDecimal creditoPendiente = null;
		while (itMaquinas.hasNext()){
			maquina = itMaquinas.next();
			infoTicketMaquinaBean = infoMaquinas.get(maquina.getIdMaquina());
			contador = infoTicketMaquinaBean.getContador();
			contadorAnterior = infoTicketMaquinaBean.getContadorAnterior();
			if (contador != null && contadorAnterior != null){
				entradaActual = contador.getEntrada();
				salidaActual = contador.getSalida();
				entradaAnt = contadorAnterior.getEntrada();
				salidaAnt = contadorAnterior.getSalida();
				difEntrada = entradaActual.subtract(entradaAnt).abs();
				difSalida = salidaActual.subtract(salidaAnt).abs();
			}
			sb.append(maquina.getNombre().toUpperCase() + separador + maquina.getCodigoMaquina() + cambioLinea);
			sb.append(separadorSeccion);
			if (contador != null && contadorAnterior != null){
				sb.append("E.Act: " + entradaActual.toString() + separador + "S.Act: " + salidaActual.toString() + cambioLinea);
				sb.append("E.Ant: " + entradaAnt.toString() + separador + "S.Ant: " + salidaAnt.toString() + cambioLinea);
				sb.append("D.Ent: " + difEntrada.toString() + separador + "D.Sal: " + difSalida.toString() + cambioLinea);
				if (!StringUtil.isNullOrEmptyOrBlankString(contador.getComentarios())){
					sb.append("Com: " + contador.getComentarios());
				}
			} else {
				sb.append("No hay contadores cargados" + cambioLinea);
			}
			if (maquina.getCargas() != null){
				sb.append("Arqueo: " + maquina.getCargas().setScale(2).toPlainString() + cambioLinea);
			} else {
				sb.append("Arqueo: Sin definir" + cambioLinea);
			}
			sb.append(separadorSeccion);
		}
		itMaquinas = maquinas.iterator();
		maquina = null;
		while (itMaquinas.hasNext()){
			maquina = itMaquinas.next();
			infoTicketMaquinaBean = infoMaquinas.get(maquina.getIdMaquina());
			liquidacion = infoTicketMaquinaBean.getLiquidacion();
			sb.append(maquina.getNombre().toUpperCase() +": ");
			if (liquidacion != null){
				sb.append(liquidacion.getBruto().setScale(2).toPlainString() + separador);
			} else {
				sb.append("No hay contadores cargados" + separador);
			}
			if (liquidacion != null && liquidacion.getFallos() != null && liquidacion.getFallos().compareTo(BigDecimal.ZERO) > 0){
				sb.append("Varios: " + liquidacion.getFallos().setScale(2).toPlainString() + cambioLinea);
			} else {
				sb.append(cambioLinea);
			}
		}
		sb.append(separadorSeccion);
		sb.append("BRUTO: " + ticket.getTotal().setScale(2).toPlainString() + separador + 
				"FECHA: " + DateUtil.getStringFromDate(ticket.getFecha(), "dd/MM/yy") + cambioLinea);
		sb.append("- TASA: " + ticket.getRetencion().setScale(2).toPlainString() + cambioLinea);
		total = ticket.getTotal().subtract(ticket.getRetencion());
		sb.append("TOTAL: " + total.setScale(2).toPlainString() + cambioLinea);
		if (credito != null){
			sb.append(separadorSeccion);
			sb.append("----------------------------" + cambioLinea);
			sb.append("CREDITO" + cambioLinea);
			sb.append("Cuota: ");
			creditoPendiente = ticket.getCreditoPendienteInicial();
			if (creditoPendiente == null) {
				creditoPendiente = BigDecimal.ZERO;
			}
			if (ticket.getCuotaCredito() != null){
				sb.append(ticket.getCuotaCredito().setScale(2).toPlainString() + cambioLinea);
				creditoPendiente = creditoPendiente.subtract(ticket.getCuotaCredito());
			} else {
				sb.append(BigDecimal.ZERO.setScale(2).toPlainString() + cambioLinea);
			}
			sb.append("Imp.Pend: " + creditoPendiente.setScale(2).toPlainString() + cambioLinea);
			sb.append("----------------------------" + cambioLinea);
			sb.append(separadorSeccion);
		}
		sb.append("LOCAL: " + ticket.getEstablecimiento().setScale(2).toPlainString() + cambioLinea);
		sb.append("E.OPER: " + ticket.getNeto().setScale(2).toPlainString() + cambioLinea);
		sb.append(separadorSeccion);
		sb.append("CONFORME" + cambioLinea);
		sb.append(separadorSeccion);
		sb.append(separadorSeccion);
		return sb;
	}
	
	@RequestMapping(value={"/adminMaquinas.html"})
	public String webAdminMaquinas(HttpServletRequest request, ModelMap model){
		return "adminMaquinas";
	}
	
	@RequestMapping(value={"/createOrUpdateMaquina.html"})
	public ModelAndView webCreateOrUpdateMaquina(HttpServletRequest request, ModelMap model,
			@RequestParam(value="idMaquina", required=false) Long idMaquina){
		Maquina maquina = null;
		List<Establecimiento> establecimientos = null;
		List<TipoMaquina> tiposMaquina = rs.getTiposMaquina();
		List<EmpresaMaquina> empresasMaquina = rs.getEmpresasMaquina();
		if (idMaquina != null) {
			maquina = rs.getMaquinaById(idMaquina);
		} else {
			establecimientos = new ArrayList<Establecimiento>();
		}
		CreateOrUpdateMaquinaBean createOrUpdateMaquinaBean = new CreateOrUpdateMaquinaBean();
		createOrUpdateMaquinaBean.setMaquina(maquina);
		createOrUpdateMaquinaBean.setEstablecimientos(establecimientos);
		createOrUpdateMaquinaBean.setTiposMaquina(tiposMaquina);
		createOrUpdateMaquinaBean.setEmpresasMaquina(empresasMaquina);
		return new ModelAndView("createOrUpdateMaquina", "createOrUpdateMaquinaBean", createOrUpdateMaquinaBean);
	}
	
	@RequestMapping(value={"/establecimientosEmpresaMaquina.html"})
	public ModelAndView establecimientosEmpresaMaquinaOptions(HttpServletRequest request, ModelMap model,
			@RequestParam(value="idEmpresaMaquina") Long idEmpresaMaquina){
		List<Establecimiento> establecimientos = rs.getEstablecimientosByIdEmpresaMaquina(idEmpresaMaquina);
		return new ModelAndView("establecimientosEmpresaMaquina", "establecimientos", establecimientos);
	}
	
	@RequestMapping(value={"/adminEstablecimientos.html"})
	public String webAdminEstablecimientos(HttpServletRequest request, ModelMap model){
		return "adminEstablecimientos";
	}
	
	@RequestMapping(value={"/createOrUpdateEstablecimiento.html"})
	public ModelAndView webCreateOrUpdateEstablecimiento(HttpServletRequest request, ModelMap model,
			@RequestParam(value="idEstablecimiento", required=false) Long idEstablecimiento){
		Establecimiento establecimiento = null;
		List<EmpresaMaquina> empresasMaquina = rs.getEmpresasMaquina();
		CreateOrUpdateEstablecimientoBean createOrUpdateEstablecimientoBean = new CreateOrUpdateEstablecimientoBean();
		if (idEstablecimiento != null) {
			establecimiento = rs.getEstablecimientoById(idEstablecimiento);
			createOrUpdateEstablecimientoBean.setTieneMaquinas(rs.getTieneMaquinas(idEstablecimiento));
		}
		createOrUpdateEstablecimientoBean.setEstablecimiento(establecimiento);
		createOrUpdateEstablecimientoBean.setEmpresasMaquina(empresasMaquina);
		return new ModelAndView("createOrUpdateEstablecimiento", "createOrUpdateEstablecimientoBean", createOrUpdateEstablecimientoBean);
	}
}