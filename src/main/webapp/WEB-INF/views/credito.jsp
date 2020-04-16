<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.rms.recreativos.service.EstablecimientoBean" %>
<%@ page import="com.rms.recreativos.entity.Establecimiento" %>
<%@ page import="com.rms.recreativos.entity.Credito" %>
<%@ page import="com.rms.recreativos.util.DateUtil" %>
<%@ page import="java.math.BigDecimal" %>
<%
EstablecimientoBean establecimientoBean = (EstablecimientoBean) request.getAttribute("establecimientoBean");
Establecimiento establecimiento = establecimientoBean.getEstablecimiento();
Credito credito = establecimientoBean.getCredito();
Long idEstablecimiento = establecimiento.getIdEstablecimiento();
String fecha = DateUtil.getStringFromDate(DateUtil.getNow(), "dd/MM/yyyy");
String importeInicial = BigDecimal.ZERO.setScale(2).toPlainString();
String importePendiente = BigDecimal.ZERO.setScale(2).toPlainString();
String cuota = BigDecimal.ZERO.setScale(2).toPlainString();
String idCredito = "";
if (credito != null){
	fecha = DateUtil.getStringFromDate(credito.getFecha(), "dd/MM/yyyy");
	importeInicial = credito.getImporteInicial().setScale(2).toPlainString();
	importePendiente = credito.getImportePendiente().setScale(2).toPlainString();
	cuota = credito.getCuota().setScale(2).toPlainString();
	idCredito = credito.getIdCredito().toString();
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
            	<div id="formCredito" class="form-group">
            		<table class="formCredito">
            			<tr>
            				<td colspan="3" height="5">&nbsp;</td>
            			</tr>
            			<tr>
            				<td>Fecha</td>
            				<td align="left"><input type="text" class="form-control" name="fecha" id="fecha" value="<%=fecha %>" disabled /></td>
            			</tr>
            			<tr>
            				<td>Importe Inicial</td>
            				<td>
            					<input type="number" step="0.01" class="form-control input-number" onChange="copiaImporte()" name="importeInicial" id="importeInicial" value="<%=importeInicial %>" />
            				</td>
            			</tr>
            			<tr>
            				<td>Importe Pendiente</td>
            				<td>
            					<input type="number" step="0.01" class="form-control input-number" name="importePendiente" id="importePendiente" value="<%=importePendiente %>" />
            				</td>
            			</tr>
            			<tr>
            				<td>Cuota</td>
            				<td>
            					<input type="number" step="0.01" class="form-control input-number" name="cuota" id="cuota" value="<%=cuota %>" />
            				</td>
            			</tr>
            			<tr>
            				<td colspan="2" align="right" style="padding-top:15px; padding-right:15px;">
            					<input type="hidden" name="idCredito" id="idCredito" value="<%=idCredito %>" />
            					<button type="button" onClick="GotoEstablecimiento(<%=idEstablecimiento%>)" class="btn btn-primary btn-xs">Volver</button>
            					<button type="button" onClick="SaveCredito(<%=idEstablecimiento%>)" class="btn btn-primary btn-xs">Guardar</button>
            				</td>
            			</tr>
            			<tr>
            				<td colspan="2" align="center" style="padding-top:15px;">
            					<div id="SaveResultCredito" class="resultHide">&nbsp;</div>
            				</td>
            			</tr>
            		</table>
            	</div>
            </div>
            </div>
<script type="text/javascript">
	var idCredito = '<%=idCredito %>';
	var creditoNuevo = false;
	if (idCredito === ''){
		creditoNuevo = true;
	}
	var campoImpIni = $("#importeInicial");
	var campoImpPend = $("#importePendiente");
	if (creditoNuevo){
		campoImpPend.prop('disabled', true);
	}
	function copiaImporte(){
		if (creditoNuevo){
			campoImpPend.val(campoImpIni.val());
		}
	}
</script>