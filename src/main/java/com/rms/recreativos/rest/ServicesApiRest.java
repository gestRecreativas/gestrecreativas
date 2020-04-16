package com.rms.recreativos.rest;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.sql.ordering.antlr.GeneratedOrderByFragmentRendererTokenTypes;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
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
import com.rms.recreativos.entity.Usuario;
import com.rms.recreativos.mail.MailUtil;
import com.rms.recreativos.service.AdminMaquinasBean;
import com.rms.recreativos.service.PropertiesService;
import com.rms.recreativos.service.RecreativosService;
import com.rms.recreativos.util.DateUtil;
import com.rms.recreativos.util.ListUtil;

@RestController
public class ServicesApiRest {

	private static final Logger logger = Logger.getLogger(ServicesApiRest.class);
	
	@Autowired
    private RecreativosService rs;
	@Autowired
	private RecreativosDAO RecreativosDAO;
	@Autowired
	MailUtil mailUtil;
	@Autowired
	PropertiesService propertiesService;
	
	@RequestMapping(value = "/loginAction", method = RequestMethod.POST, produces="application/json")
    public String loginAction(@RequestParam(value="us", defaultValue="") String us,
    		@RequestParam(value="pwd", defaultValue="") String pwd){
		logger.debug("INCIO loginAction");
		Usuario usuario = RecreativosDAO.getUsuarioByLoginPassword(us, pwd);
		JSONObject jsonObj = new JSONObject();
		try {
			if (usuario != null){
				jsonObj.put("Status", "200");
				jsonObj.put("Nombre", usuario.getNombreCompleto());
				jsonObj.put("IsAdmin", usuario.getAdmin() == 1? true : false);
			} else {
				jsonObj.put("Status", "403");
			}
		} catch (JSONException e) {
			logger.error("ERROR: Se ha producido un error en login: " + e.getMessage(), e);
			e.printStackTrace();
		}
		return jsonObj.toString();
    }
	
	@RequestMapping(value = "/getTotalEstablecimientos", method = RequestMethod.GET, produces="application/json")
    public String EstablecimientosTotalActionGet(
    		@RequestParam(value="idUsuario") Long idUsuario){
		logger.debug("INCIO getTotalEstablecimientos");
		List<Establecimiento> establecimientosTotal = RecreativosDAO.getEstablecimientosTotal(idUsuario);
		if (!ListUtil.nullOrEmptyList(establecimientosTotal)){
			try {
				ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				return mapper.writeValueAsString(establecimientosTotal);
			} catch (JsonProcessingException e) {
				logger.error("ERROR getTotalEstablecimientos: " + e.getMessage(), e);
				e.printStackTrace();
				return "[]";
			}
		} else {
			return "[]";
		}
    }
	
	@RequestMapping(value = "/getEstablecimientosUsuario", method = RequestMethod.GET, produces="application/json")
    public String EstablecimientosUsuarioActionGet(
    		@RequestParam(value="idUsuario") Long idUsuario){
		logger.debug("INCIO getEstablecimientosUsuario - idUsuario: " + idUsuario);
		List<Establecimiento> establecimientosUsuario = RecreativosDAO.getEstablecimientosUsuario(idUsuario);
		if (!ListUtil.nullOrEmptyList(establecimientosUsuario)){
			try {
				ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				return mapper.writeValueAsString(establecimientosUsuario);
			} catch (JsonProcessingException e) {
				logger.error("ERROR getEstablecimientosUsuario: " + e.getMessage(), e);
				e.printStackTrace();
				return "[]";
			}
		} else {
			return "[]";
		}
    }
	
	@RequestMapping(value = "/getMaquinasEstablecimiento", method = RequestMethod.GET, produces="application/json")
    public String MaquinasEstablecimientoActionGet(
    		@RequestParam(value="idEstablecimiento") Long idEstablecimiento){
		logger.debug("INCIO getMaquinasEstablecimiento - idEstablecimiento: " + idEstablecimiento);
		List<Maquina> maquinasEstablecimiento = RecreativosDAO.getMaquinasByIdEstablecimiento(idEstablecimiento);
		if (!ListUtil.nullOrEmptyList(maquinasEstablecimiento)){
			try {
				ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				return mapper.writeValueAsString(maquinasEstablecimiento);
			} catch (JsonProcessingException e) {
				logger.error("ERROR getMaquinasEstablecimiento: " + e.getMessage(), e);
				e.printStackTrace();
				return "[]";
			}
		} else {
			return "[]";
		}
    }
	
	@RequestMapping(value = "/getAdminMaquinas", method = RequestMethod.GET, produces="application/json")
    public String AdminMaquinasActionGet(
    		@RequestParam(value="idUsuario") Long idUsuario){
		logger.debug("INCIO getAdminMaquinas");
		List<AdminMaquinasBean> maquinasTotal = RecreativosDAO.getMaquinasTotal(idUsuario);
		if (!ListUtil.nullOrEmptyList(maquinasTotal)){
			try {
				ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				return mapper.writeValueAsString(maquinasTotal);
			} catch (JsonProcessingException e) {
				logger.error("ERROR getAdminMaquinas: " + e.getMessage(), e);
				e.printStackTrace();
				return "[]";
			}
		} else {
			return "[]";
		}
    }
	
	@RequestMapping(value = "/getAdminEstablecimientos", method = RequestMethod.GET, produces="application/json")
    public String AdminEstablecimientosActionGet(
    		@RequestParam(value="idUsuario") Long idUsuario){
		logger.debug("INCIO getAdminEstablecimientos");
		List<Establecimiento> establecimientos = RecreativosDAO.getEstablecimientosAdmin(idUsuario);
		if (!ListUtil.nullOrEmptyList(establecimientos)){
			try {
				ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				return mapper.writeValueAsString(establecimientos);
			} catch (JsonProcessingException e) {
				logger.error("ERROR getAdminEstablecimientos: " + e.getMessage(), e);
				e.printStackTrace();
				return "[]";
			}
		} else {
			return "[]";
		}
    }
	
	@RequestMapping(value = "/postContador", method = RequestMethod.POST, produces="application/json")
    public String saveContadorAction(@RequestParam(value="idMaquina") Long idMaquina,
    		@RequestParam(value="entradaAnt") BigInteger entradaAnt,
    		@RequestParam(value="entrada") BigInteger entrada,
    		@RequestParam(value="salidaAnt") BigInteger salidaAnt,
    		@RequestParam(value="salida") BigInteger salida,
    		@RequestParam(value="comentarios", defaultValue="") String comentarios,
    		@RequestParam(value="idUsuario") Long idUsuario){
		logger.debug("INCIO saveContadorAction");
		Date fecha = DateUtil.getNow();
		Contador contadorAnt = RecreativosDAO.getLastContador(idMaquina);
		if (contadorAnt == null){
			Calendar fechaSemanaAnt = Calendar.getInstance();
			fechaSemanaAnt.add(Calendar.DAY_OF_MONTH, -7);
			Date fechaAnt = fechaSemanaAnt.getTime();
			contadorAnt = new Contador();
			contadorAnt.setIdMaquina(idMaquina);
			contadorAnt.setFecha(fechaAnt);
			contadorAnt.setEntrada(entradaAnt);
			contadorAnt.setSalida(salidaAnt);
			contadorAnt.setComentarios("");
			contadorAnt.setIdUsuario(idUsuario);
			Maquina maq = RecreativosDAO.getMaquinaById(idMaquina);
			if(maq!=null)contadorAnt.setIdEstablecimiento(maq.getIdEstablecimiento());
			RecreativosDAO.createContador(contadorAnt);
		}
		Contador contador = RecreativosDAO.getContadorActual(idMaquina);
		boolean guardado = false;
		JSONObject jsonObj = new JSONObject();
		if (contador == null){
			contador = new Contador();
			contador.setIdMaquina(idMaquina);
			contador.setFecha(fecha);
			contador.setEntrada(entrada);
			contador.setSalida(salida);
			contador.setComentarios(comentarios);
			contador.setIdUsuario(idUsuario);
			Maquina maq = RecreativosDAO.getMaquinaById(idMaquina);
			if(maq!=null)contador.setIdEstablecimiento(maq.getIdEstablecimiento());
			guardado = RecreativosDAO.createContador(contador);
		} else {
			contador.setEntrada(entrada);
			contador.setSalida(salida);
			contador.setComentarios(comentarios);
			contador.setIdUsuario(idUsuario);
			guardado = RecreativosDAO.updateContador(contador);
		}
		try {
			if (guardado){
				jsonObj.put("Resultado", "OK");
				JSONObject liquidacion = rs.getLiquidacionMaquina(idMaquina, idUsuario, false);
				jsonObj.put("Liquidacion", liquidacion);
			} else {
				jsonObj.put("Resultado", "ERROR");
			}
		} catch(Exception e) {
			logger.error("ERROR saveContadorAction: " + e.getMessage(), e);
			e.printStackTrace();
		}
		return jsonObj.toString();
    }
	
	@RequestMapping(value = "/postLiquidacion", method = RequestMethod.POST, produces="application/json")
    public String saveLiquidacionAction(@RequestParam(value="idMaquina") Long idMaquina,
    		@RequestParam(value="bruto") BigDecimal bruto,
    		@RequestParam(value="fallos") BigDecimal fallos,
    		@RequestParam(value="recaudacion") BigDecimal recaudacion,
    		@RequestParam(value="retencion") BigDecimal retencion,
    		@RequestParam(value="porcentajeEst") BigDecimal porcentajeEst,
    		@RequestParam(value="pagoEst") BigDecimal pagoEst,
    		@RequestParam(value="neto") BigDecimal neto,
    		@RequestParam(value="idUsuario") Long idUsuario){
		logger.debug("INCIO saveLiquidacionAction");
		boolean hayLiquidacion = true;
		Liquidacion liquidacion = RecreativosDAO.getLiquidacionActual(idMaquina);
		if (liquidacion != null){
			liquidacion.setBruto(bruto);
			liquidacion.setFallos(fallos);
			liquidacion.setRecaudacion(recaudacion);
			liquidacion.setRetencion(retencion);
			liquidacion.setPorcentajeEst(porcentajeEst);
			liquidacion.setPagoEst(pagoEst);
			liquidacion.setNeto(neto);
			liquidacion.setIdUsuario(idUsuario);
			liquidacion = RecreativosDAO.updateLiquidacion(liquidacion);
		} else {
			hayLiquidacion = false;
		}
		JSONObject jsonObj = new JSONObject();
		try {
			if (hayLiquidacion && liquidacion != null){
				jsonObj.put("Resultado", "OK");
				JSONObject liquidacionJSON = rs.getLiquidacionMaquina(idMaquina, idUsuario, true);
				jsonObj.put("Liquidacion", liquidacionJSON);
			} else {
				if (!hayLiquidacion) {
					jsonObj.put("Resultado", "ERROR_NO_CONTADORES");
				} else {
					jsonObj.put("Resultado", "ERROR");
				}
			}
		} catch(Exception e) {
			logger.error("ERROR saveLiquidacionAction: " + e.getMessage(), e);
			e.printStackTrace();
		}
		return jsonObj.toString();
	}
	
	@RequestMapping(value = "/postCredito", method = RequestMethod.POST, produces="application/json")
    public String saveCreditoAction(@RequestParam(value="idEstablecimiento") Long idEstablecimiento,
    		@RequestParam(value="idCredito") Long idCredito,
    		@RequestParam(value="importeInicial") BigDecimal importeInicial,
    		@RequestParam(value="importePendiente") BigDecimal importePendiente,
    		@RequestParam(value="cuota") BigDecimal cuota,
    		@RequestParam(value="idUsuario") Long idUsuario){
		logger.debug("INCIO saveCreditoAction");
		Credito credito = null;
		boolean tieneMaquinas = true;
		if (idCredito != null){
			credito = RecreativosDAO.getCreditoById(idCredito);
			credito.setImporteInicial(importeInicial);
			credito.setImportePendiente(importePendiente);
			credito.setCuota(cuota);
			credito.setIdUsuario(idUsuario);
			credito.setFechaCobro(null);
			credito.setCreditoInicialActual(importePendiente);
			credito = RecreativosDAO.updateCredito(credito);
		} else {
			List<Maquina> maquinas = RecreativosDAO.getMaquinasByIdEstablecimiento(idEstablecimiento);
			if (!ListUtil.nullOrEmptyList(maquinas)){
				Maquina maquina = (Maquina) maquinas.get(0);
				credito = new Credito();
				credito.setIdEmpresaMaquina(maquina.getIdEmpresaMaquina());
				credito.setIdEstablecimiento(idEstablecimiento);
				credito.setFecha(DateUtil.getNow());
				credito.setImporteInicial(importeInicial);
				credito.setImportePendiente(importePendiente);
				credito.setCuota(cuota);
				credito.setFechaCobro(null);
				credito.setCreditoInicialActual(importePendiente);
				credito.setIdUsuario(idUsuario);
				credito = RecreativosDAO.createCredito(credito);
			} else {
				tieneMaquinas = false;
			}
		}
		JSONObject jsonObj = new JSONObject();
		try {
			if (credito != null){
				jsonObj.put("Resultado", "OK");
			} else if (!tieneMaquinas){
				jsonObj.put("Resultado", "ERROR_NO_MAQUINAS");
			} else {
				jsonObj.put("Resultado", "ERROR");
			}
		} catch(Exception e) {
			logger.error("ERROR saveCreditoAction: " + e.getMessage(), e);
			e.printStackTrace();
		}
		return jsonObj.toString();
	}
	
	@RequestMapping(value = "/postDeleteContadores", method = RequestMethod.POST, produces="application/json")
    public String deleteContadorAction(@RequestParam(value="idMaquina") Long idMaquina){
		logger.debug("INCIO deleteContadorAction");
		boolean borrado = true;
		Maquina maquina = RecreativosDAO.getMaquinaById(idMaquina);
		Contador contador = RecreativosDAO.getContadorActual(idMaquina);
		if (contador != null){
			borrado = borrado && RecreativosDAO.deleteContador(contador.getIdContador());
		}
		Liquidacion liquidacion = RecreativosDAO.getLiquidacionActual(idMaquina);
		if (liquidacion != null){
			borrado = borrado && RecreativosDAO.deleteLiquidacion(liquidacion.getIdLiquidacion());
		}
		Ticket ticket = RecreativosDAO.getTicketActual(maquina.getIdEstablecimiento());
		if (ticket != null){
			borrado = borrado && RecreativosDAO.deleteTicket(ticket.getIdTicket());
		}
		JSONObject jsonObj = new JSONObject();
		try {
			if (borrado){
				jsonObj.put("Resultado", "OK");
			} else {
				jsonObj.put("Resultado", "ERROR");
			}
		} catch(Exception e) {
			logger.error("ERROR deleteContadorAction: " + e.getMessage(), e);
			e.printStackTrace();
		}
		return jsonObj.toString();
	}
	
	@RequestMapping(value = "/postMailTicket", method = RequestMethod.POST, produces="application/json")
    public String sendMailTicket(@RequestParam(value="idEstablecimiento") String idEstablecimiento,
    		@RequestParam(value="establecimiento") String establecimiento,
    		@RequestParam(value="fecha") String fecha,
    		@RequestParam(value="html") String html,
    		@RequestParam(value="idUsuario") Long idUsuario){
		logger.debug("INCIO sendMailTicket");
		boolean enviado = true;
		File carpetaRaiz = new File(propertiesService.getRutaFicherosTicket());
		if (!carpetaRaiz.exists() || !carpetaRaiz.isDirectory()){
			carpetaRaiz.mkdir();
		}
		EmpresaGestion empresaGestion = rs.getEmpresaGestionByIdUsuario(idUsuario);
		File carpetaEstablecimiento = new File(propertiesService.getRutaFicherosTicket() + File.separator +
				empresaGestion.getIdEmpresaGestion()+"_"+idEstablecimiento + "_" + establecimiento);
		if (!carpetaEstablecimiento.exists() || !carpetaEstablecimiento.isDirectory()){
			carpetaEstablecimiento.mkdir();
		}
		File fichero = null;
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fichero = new File(carpetaEstablecimiento, establecimiento + "_" + fecha + ".html");
			fw = new FileWriter(fichero);
			pw = new PrintWriter(fw);
			pw.print(getFileContent(html).toString());
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
		
		String[] emailTo = new String[1];
		emailTo[0] = empresaGestion.getEmail();
		try {
			Date date = DateUtil.getDateFromString(fecha, "ddMMyyyy");
			fichero = new File(carpetaEstablecimiento, establecimiento + "_" + fecha + ".html");
			mailUtil.sendMail("Ticket de " + establecimiento + " - " + DateUtil.getStringFromDate(date, "dd-MM-yyyy"),
					"Buenos días.\n\nAdjunto se envía el ticket del establecimiento " + establecimiento + " de la fecha " + DateUtil.getStringFromDate(date, "dd-MM-yyyy"),
					fichero, null, emailTo, Boolean.FALSE);
		} catch (Exception e){
			enviado = false;
			logger.error("ERROR sendMailTicket: " + e.getMessage());
			e.printStackTrace();
		}
		JSONObject jsonObj = new JSONObject();
		try {
			if (enviado){
				jsonObj.put("Resultado", "OK");
			} else {
				jsonObj.put("Resultado", "ERROR");
			}
		} catch(Exception e) {
			logger.error("ERROR sendMailTicket: " + e.getMessage(), e);
			e.printStackTrace();
		}
		return jsonObj.toString();
	}
	
	@RequestMapping(value = "/postCreateOrUpdateMaquina", method = RequestMethod.POST, produces="application/json")
    public String createOrUpdateMaquina(@RequestParam(value="idMaquina", required=false) Long idMaquina,
    		@RequestParam(value="nombre") String nombre,
    		@RequestParam(value="codigo") String codigo,
    		@RequestParam(value="porcentajeEst") BigDecimal porcentajeEst,
    		@RequestParam(value="cargas") BigDecimal cargas,
    		@RequestParam(value="tipoMaquina") Long idTipoMaquina,
    		@RequestParam(value="empresaMaquina") Long idEmpresaMaquina,
    		@RequestParam(value="establecimiento") Long idEstablecimiento,
    		@RequestParam(value="idUsuario") Long idUsuario){
		logger.debug("INCIO createOrUpdateMaquina");
		Maquina maquina = null;
		if (idMaquina != null) {
			maquina = RecreativosDAO.getMaquinaById(idMaquina);
		}
		EmpresaGestion empresaGestion = rs.getEmpresaGestionByIdUsuario(idUsuario);
		boolean guardado = false;
		JSONObject jsonObj = new JSONObject();
		if (maquina == null){
			maquina = new Maquina();
			maquina.setNombre(nombre);
			maquina.setCodigoMaquina(codigo);
			maquina.setPorcentajeEstablecimiento(porcentajeEst);
			maquina.setCargas(cargas);
			maquina.setIdTipoMaquina(idTipoMaquina);
			maquina.setIdEmpresaMaquina(idEmpresaMaquina);
			maquina.setIdEstablecimiento(idEstablecimiento);
			maquina.setIdEmpresaGestion(empresaGestion.getIdEmpresaGestion());
			guardado = RecreativosDAO.createMaquina(maquina);
		} else {
			maquina.setNombre(nombre);
			maquina.setCodigoMaquina(codigo);
			maquina.setPorcentajeEstablecimiento(porcentajeEst);
			maquina.setCargas(cargas);
			maquina.setIdTipoMaquina(idTipoMaquina);
			maquina.setIdEmpresaMaquina(idEmpresaMaquina);
			maquina.setIdEstablecimiento(idEstablecimiento);
			guardado = RecreativosDAO.updateMaquina(maquina);
		}
		try {
			if (guardado){
				jsonObj.put("Resultado", "OK");
			} else {
				jsonObj.put("Resultado", "ERROR");
			}
		} catch(Exception e) {
			logger.error("ERROR createOrUpdateMaquina: " + e.getMessage(), e);
			e.printStackTrace();
		}
		return jsonObj.toString();
    }
	
	@RequestMapping(value = "/postCreateOrUpdateEstablecimiento", method = RequestMethod.POST, produces="application/json")
    public String createOrUpdateEstablecimiento(@RequestParam(value="idEstablecimiento", required=false) Long idEstablecimiento,
    		@RequestParam(value="nombre") String nombre,
    		@RequestParam(value="codigoEstablecimiento") String codigoEstablecimiento,
    		@RequestParam(value="email") String email,
    		@RequestParam(value="bote") BigDecimal bote,
    		@RequestParam(value="empresaMaquina") Long idEmpresaMaquina){
		logger.debug("INCIO createOrUpdateEstablecimiento");
		Establecimiento establecimiento = null;
		if (idEstablecimiento != null) {
			establecimiento = RecreativosDAO.getEstablecimientoById(idEstablecimiento);
		}
		boolean guardado = false;
		JSONObject jsonObj = new JSONObject();
		if (establecimiento == null){
			establecimiento = new Establecimiento();
			establecimiento.setNombre(nombre);
			establecimiento.setCodigoEstablecimiento(codigoEstablecimiento);
			establecimiento.setEmail(email);
			establecimiento.setBote(bote);
			establecimiento.setIdEmpresaMaquina(idEmpresaMaquina);
			EmpresaMaquina empMaq = RecreativosDAO.getEmpresaMaquinaById(idEmpresaMaquina);
			if(empMaq!=null)establecimiento.setIdEmpresaGestion(empMaq.getIdEmpresaGestion());
			guardado = RecreativosDAO.createEstablecimiento(establecimiento);
		} else {
			establecimiento.setNombre(nombre);
			establecimiento.setCodigoEstablecimiento(codigoEstablecimiento);
			establecimiento.setEmail(email);
			establecimiento.setBote(bote);
			establecimiento.setIdEmpresaMaquina(idEmpresaMaquina);
			guardado = RecreativosDAO.updateEstablecimiento(establecimiento);
		}
		try {
			if (guardado){
				jsonObj.put("Resultado", "OK");
			} else {
				jsonObj.put("Resultado", "ERROR");
			}
		} catch(Exception e) {
			logger.error("ERROR createOrUpdateEstablecimiento: " + e.getMessage(), e);
			e.printStackTrace();
		}
		return jsonObj.toString();
    }
	
	private StringBuffer getFileContent(String html){
		StringBuffer sb = new StringBuffer();
		sb.append("<!DOCTYPE html>");
		sb.append("<html lang=\"en\">");
		sb.append("<head>");
		sb.append("<meta charset=\"UTF-8\">");
		sb.append("<link href=\"http://www.montamaquinas.es/vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">");
		sb.append("<link href=\"http://www.montamaquinas.es/vendor/metisMenu/metisMenu.min.css\" rel=\"stylesheet\">");
		sb.append("<link href=\"http://www.montamaquinas.es/css/sb-admin-2.min.css\" rel=\"stylesheet\">");
		sb.append("<link href=\"http://www.montamaquinas.es/vendor/font-awesome/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\">");
		sb.append("<link href=\"http://www.montamaquinas.es/css/recreativos.css\" rel=\"stylesheet\">");
		sb.append("<title>Montamaquinas</title>");
		sb.append("<style>");
		sb.append("	.form-control {");
		sb.append("		width: 80%;");
		sb.append("	}");
		sb.append("</style>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<div id=\"wrapper\" style=\"width: 80%; margin-left: 20px; margin-right: 20px;\">");
		sb.append("	<div class=\"container-fluid\">");
		sb.append(html);
		sb.append("	</div>");
		sb.append("</div>");
		sb.append("</body>");
		sb.append("</html>");
		return sb;
	}
}