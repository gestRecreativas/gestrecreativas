<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.rms.recreativos.service.HistoricoBean" %>
<%@ page import="com.rms.recreativos.entity.Establecimiento" %>
<%@ page import="com.rms.recreativos.entity.Ticket" %>
<%@ page import="com.rms.recreativos.util.DateUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%
HistoricoBean historicoBean = (HistoricoBean) request.getAttribute("historicoBean");
Establecimiento establecimiento = historicoBean.getEstablecimiento();
Long idEstablecimiento = establecimiento.getIdEstablecimiento();
List<Ticket> tickets = historicoBean.getTickets();
String fechaInicioFiltro = historicoBean.getFechaInicioFiltro();
if (fechaInicioFiltro == null){
	fechaInicioFiltro = "";
}
String fechaFinFiltro = historicoBean.getFechaFinFiltro();
if (fechaFinFiltro == null){
	fechaFinFiltro = "";
}
%>
        	<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div width="100%" class="page-header">
						<h4><%= establecimiento.getNombre() %></h4>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="container">
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<label>Fecha desde:</label>
							<input type="text" class="form-control" style="width:100%;" placeholder="dd-mm-yyyy" name="fechaInicio" id="fechaInicio" size="12" value="<%= fechaInicioFiltro %>" />
						</div>
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<label>Fecha hasta:</label>
							<input type="text" class="form-control" style="width:100%;" placeholder="dd-mm-yyyy" name="fechaFin" id="fechaFin" size="12" value="<%= fechaFinFiltro %>" />
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="text-align: right; margin-top: 10px; margin-right: 10px;">
							<button type="button" onClick="BuscarTickets(<%= idEstablecimiento %>)" class="btn btn-primary btn-xs">Buscar</button>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="border-bottom: 1px solid #eee; margin-top: 10px;">
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<table cellpadding="0" cellspacing="0" border="0" align="center" style="margin-top: 10px;">
					<%
						if (tickets != null){
							Iterator<Ticket> itTickets = tickets.iterator();
							Ticket ticket = null;
							while (itTickets.hasNext()){
								ticket = itTickets.next();
					%>
						<tr>
							<td><%= DateUtil.getStringFromDate(ticket.getFecha(), "dd-MM-yyyy")%>&nbsp;<%=ticket.getHora() %></td>
							<td>
								<a href="javascript:VerTicket(<%= idEstablecimiento %>, <%= ticket.getIdTicket() %>)"><i class="glyphicon glyphicon-eye-open" style="padding-left:5px;padding-top:8px;font-size: 16px;"></i></a>
							</td>
						</tr>
					<%
							}
						}
					%>
					</table>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="text-align: right; margin-right: 10px;">
					<button type="button" onClick="GotoEstablecimiento(<%= idEstablecimiento %>)" class="btn btn-primary btn-xs">Volver</button>
				</div>
			</div>
			</div>