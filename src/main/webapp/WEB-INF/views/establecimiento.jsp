<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.rms.recreativos.service.EstablecimientoBean" %>
<%@ page import="com.rms.recreativos.entity.Establecimiento" %>
<%@ page import="com.rms.recreativos.entity.Credito" %>
<%@ page import="java.math.BigDecimal" %>
<%
EstablecimientoBean establecimientoBean = (EstablecimientoBean) request.getAttribute("establecimientoBean");
Establecimiento establecimiento = establecimientoBean.getEstablecimiento();
Credito credito = establecimientoBean.getCredito();
Long idEstablecimiento = establecimiento.getIdEstablecimiento();
BigDecimal creditoPendiente = BigDecimal.ZERO.setScale(2);
if (credito != null && credito.getImportePendiente() != null){
	creditoPendiente = credito.getImportePendiente().setScale(2);
}
%>
        	<div class="container-fluid">
			<div class="row" style="padding-top: 20px">
			</div>
			<div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <h2 class="page-header"><%= establecimiento.getNombre() %></h2>
                </div>
            </div>
            <div class="row">
            	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="display: table;">
            		<label style="display: table-cell; vertical-align: middle; text-align: right; padding-right: 10px;">Crédito:</label>
            		<div class="form-group input-group" style="margin-bottom: 0px;">
            			<span class="input-group-addon"><i class="fa fa-eur"></i></span>
            			<input type="text" class="form-control" style="width:8em;" name="creditoPendiente" id="creditoPendiente" value="<%=creditoPendiente.toPlainString() %>" disabled />
            			<button type="button" onClick="GotoCreditos(<%=idEstablecimiento%>)" class="btn btn-primary btn-xs" style="margin-left: 10px; margin-top: 6px;">CREDITOS</button>
            		</div>
            	</div>
            </div>
            <div class="row" style="padding-top: 10px">
            	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Máquinas
                        </div>
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-maquinas">
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
            	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            		<div style="text-align: right; padding-right: 10px; margin-bottom: 20px;">
            			<button type="button" onClick="GotoHistoricoTicket(<%=idEstablecimiento%>)" class="btn btn-primary btn-xs" style="margin-right: 10px;">Histórico</button>
            			<button type="button" onClick="GotoTicket(<%=idEstablecimiento%>)" class="btn btn-primary btn-xs">Ticket</button>
            		</div>
            	</div>
            </div>
            </div>