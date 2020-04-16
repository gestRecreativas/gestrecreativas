<%@page import="com.rms.recreativos.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.rms.recreativos.service.TicketBean" %>
<%@ page import="com.rms.recreativos.entity.EmpresaMaquina" %>
<%@ page import="com.rms.recreativos.entity.Establecimiento" %>
<%@ page import="com.rms.recreativos.entity.Maquina" %>
<%@ page import="com.rms.recreativos.entity.Contador" %>
<%@ page import="com.rms.recreativos.entity.Liquidacion" %>
<%@ page import="com.rms.recreativos.entity.Ticket" %>
<%@ page import="com.rms.recreativos.entity.Credito" %>
<%@ page import="com.rms.recreativos.service.InfoTicketMaquinaBean" %>
<%@ page import="com.rms.recreativos.util.DateUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="java.math.BigInteger" %>
<%@ page import="java.math.BigDecimal" %>
<%
TicketBean ticketBean = (TicketBean) request.getAttribute("ticketBean");
EmpresaMaquina empresaMaquina = ticketBean.getEmpresaMaquina();
Establecimiento establecimiento = ticketBean.getEstablecimiento();
Long idEstablecimiento = establecimiento.getIdEstablecimiento();
List<Maquina> maquinas = ticketBean.getMaquinas();
LinkedHashMap<Long, InfoTicketMaquinaBean> infoMaquinas = ticketBean.getInfoMaquinas();
Ticket ticket = ticketBean.getTicket();
Credito credito = ticketBean.getCredito();
String ficheroTxt = ticketBean.getFicheroTxt();
String fechaInicioFiltro = ticketBean.getFechaInicioFiltro();
String fechaFinFiltro = ticketBean.getFechaFinFiltro();
String fechaRecaudacion = ticketBean.getFechaRecaudacion();
%>
        	<div class="container-fluid">
			<div id="printZone">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div width="100%" class="page-header">
						<h4><%= empresaMaquina.getRazonSocial().toUpperCase() %></h4>
						<div width="100%" style="text-align: right;">
							<%= empresaMaquina.getCif() %>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<table width="100%" border="0">
						<tr>
							<td style="text-align:left;"><strong><%= establecimiento.getNombre().toUpperCase() %></strong></td>
							<td style="text-align:right;"><strong><%= establecimiento.getCodigoEstablecimiento() %></strong></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="row">
				<%
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
				%>
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<table width="100%" border="0" style="margin-top:15px;">
					<tr>
						<td style="text-align:left;"><%= maquina.getNombre().toUpperCase() %></td>
						<td style="text-align:left;"><%= maquina.getCodigoMaquina() %></td>
					</tr>
					<tr>
						<td colspan="2" style="margin-top:8px;">&nbsp;</td>
					</tr>
					<% if (contador != null && contadorAnterior != null){ %>
					<tr>
						<td>Ent. Act.:&nbsp;&nbsp;&nbsp;<%= entradaActual.toString() %></td>
						<td>Sal. Act.:&nbsp;&nbsp;&nbsp;<%= salidaActual.toString() %></td>
					</tr>
					<tr>
						<td>Ent. Ant.:&nbsp;&nbsp;&nbsp;<%= entradaAnt.toString() %></td>
						<td>Sal. Ant.:&nbsp;&nbsp;&nbsp;<%= salidaAnt.toString() %></td>
					</tr>
					<tr>
						<td>Dif. Ent.:&nbsp;&nbsp;&nbsp;<%= difEntrada.toString() %></td>
						<td>Dif. Sal.:&nbsp;&nbsp;&nbsp;<%= difSalida.toString() %></td>
					</tr>
					<%
							if (!StringUtil.isNullOrEmptyOrBlankString(contador.getComentarios())){
					%>
					<tr>
						<td colspan="2">Comentario:&nbsp;<%= contador.getComentarios() %></td>
					</tr>
					<%
							}
						} else { %>
					<tr>
						<td colspan="2">No hay contadores cargados</td>
					</tr>
					<% } %>
					<tr>
					<% if (maquina.getCargas() != null){ %>
						<td colspan="2">Arqueo:&nbsp;&nbsp;&nbsp;<%= maquina.getCargas().setScale(2).toPlainString() %></td>
					<% } else { %>
						<td colspan="2">Arqueo:&nbsp;&nbsp;&nbsp;Sin definir</td>
					<% } %>
					</tr>
				</table>
				</div>
				<%
					}
				%>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div width="100%" class="page-header" style="margin: 10px 0 10px;"></div>
					<div width="100%" class="page-header" style="margin: 10px 0 10px;"></div>
				</div>
				<table width="100%" border="0" style="margin-top:10px;">
				<%
					itMaquinas = maquinas.iterator();
					maquina = null;
					while (itMaquinas.hasNext()){
						maquina = itMaquinas.next();
						infoTicketMaquinaBean = infoMaquinas.get(maquina.getIdMaquina());
						liquidacion = infoTicketMaquinaBean.getLiquidacion();
				%>
					<tr>
						<td style="text-align:left;">
							<%= maquina.getNombre().toUpperCase() %>:&nbsp;&nbsp;&nbsp;
				<%
					if (liquidacion != null){
				%>
								<%= liquidacion.getBruto().setScale(2).toPlainString() %>
				<%
					} else {
				%>
					No hay contadores cargados
				<%
					}
				%>
						</td>
						<td>
				<%
					if (liquidacion != null && liquidacion.getFallos() != null && liquidacion.getFallos().compareTo(BigDecimal.ZERO) > 0){
				%>
					Varios:&nbsp;&nbsp;&nbsp;<%= liquidacion.getFallos().setScale(2).toPlainString() %>
				<%		
					} else {
				%>
						&nbsp;
				<%		
					}
				%>
						</td>
					</tr>
				<%
					}
				%>
					<tr>
						<td style="text-align:left;">BRUTO:&nbsp;&nbsp;&nbsp;<%= ticket.getTotal().setScale(2).toPlainString() %></td>
						<td style="text-align:right;">
							FECHA:&nbsp;&nbsp;&nbsp;<%=DateUtil.getStringFromDate(ticket.getFecha(), "dd/MM/yyyy") %>
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">
							- TASA:&nbsp;&nbsp;&nbsp;<%= ticket.getRetencion().setScale(2).toPlainString() %>
						</td>
						<td>&nbsp;</td>
					</tr>
				<%
					total = ticket.getTotal().subtract(ticket.getRetencion());
				%>
					<tr>
						<td style="text-align:left;">
							TOTAL:&nbsp;&nbsp;&nbsp;<%= total.setScale(2).toPlainString() %>
						</td>
						<td>&nbsp;</td>
					</tr>
					<%
						if (credito != null){
							BigDecimal creditoPendiente = ticket.getCreditoPendienteInicial();
							if (creditoPendiente == null) {
								creditoPendiente = BigDecimal.ZERO;
							}
					%>
					<tr>
						<td colspan="2" style="text-align:left;">
							<table width="100%" style="border: #000000 1px solid; margin-top: 10px; margin-bottom: 10px;">
								<tr>
									<td colspan="2" style="text-align:center;"><strong>CREDITO</strong></td>
								</tr>
								<tr>
									<td style="text-align:left;">Cuota cobrada:</td>
									<td style="text-align:right;">
										<% if (ticket.getCuotaCredito() != null){ 
											creditoPendiente = creditoPendiente.subtract(ticket.getCuotaCredito());
										%>
											<%= ticket.getCuotaCredito().setScale(2).toPlainString() %>
										<% } else { %>
											<%= BigDecimal.ZERO.setScale(2).toPlainString() %>
										<% } %>
									</td>
								</tr>
								<tr>
									<td style="text-align:left;">Importe pendiente:</td>
									<td style="text-align:right;"><%= creditoPendiente.setScale(2).toPlainString() %></td>
								</tr>
							</table>
						</td>
					<%
						}
					%>
					<tr>
						<td style="text-align:left;">
							LOCAL:&nbsp;&nbsp;&nbsp;<%= ticket.getEstablecimiento().setScale(2).toPlainString() %>
						</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td style="text-align:left;">
							E.OPER:&nbsp;&nbsp;&nbsp;<%= ticket.getNeto().setScale(2).toPlainString() %>
						</td>
						<td style="text-align:right;">CONFORME</td>
					</tr>
				</table>
			</div>
			</div>
			<div class="row">
				<div id="botones" class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="text-align:right; margin-top: 10px; margin-bottom: 20px;">
					<div id="ResultEnvioMail" class="resultHide">&nbsp;</div>
					<button type="button" onClick="MailTicket('<%= idEstablecimiento %>','<%= establecimiento.getNombre().toUpperCase() %>','<%=DateUtil.getStringFromDate(ticket.getFecha(), "ddMMyyyy") %>')" class="btn btn-primary btn-xs">Enviar por correo</button>
					<a href="printTicket.html?idEstablecimiento=<%= idEstablecimiento %>&establecimiento=<%= establecimiento.getNombre().toUpperCase() %>&nombreFichero=<%= ficheroTxt%>" class="btn btn-primary btn-xs">Imprimir</a>
					<% if (fechaInicioFiltro == null && fechaFinFiltro == null && fechaRecaudacion == null){ %>
						<button type="button" onClick="GotoEstablecimiento(<%= idEstablecimiento %>)" class="btn btn-primary btn-xs">Volver</button>
					<% } else if (fechaRecaudacion != null){ %>
						<button type="button" onClick="VolverRecaudaciones()" class="btn btn-primary btn-xs">Volver</button>
					<% } else { %>
						<button type="button" onClick="VolverHistoricoTickets(<%= idEstablecimiento %>, '<%= fechaInicioFiltro %>', '<%= fechaFinFiltro %>')" class="btn btn-primary btn-xs">Volver</button>
					<% } %>
				</div>
			</div>
			<form id="formRecaudaciones" name="formRecaudaciones" method="post" action="recaudaciones.html">
				<input type="hidden" id ="fecha" name="fecha" value="<%= fechaRecaudacion %>"/>
			</form>
			</div>