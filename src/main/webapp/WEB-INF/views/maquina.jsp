<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.rms.recreativos.entity.Maquina" %>
<%@ page import="com.rms.recreativos.entity.Establecimiento" %>
<%@ page import="com.rms.recreativos.entity.Contador" %>
<%@ page import="com.rms.recreativos.entity.Liquidacion" %>
<%@ page import="com.rms.recreativos.service.MaquinaBean" %>
<%@ page import="com.rms.recreativos.util.DateUtil" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.math.BigInteger" %>
<%
	MaquinaBean maquinaBean = (MaquinaBean) request.getAttribute("maquinaBean");
	Maquina maquina = maquinaBean.getMaquina();
	Establecimiento establecimiento = maquinaBean.getEstablecimiento();
	Contador contadorAnt = maquinaBean.getContadorAnt();
	String entradaAnt = "";
	String salidaAnt = "";
	if (contadorAnt != null){
		entradaAnt = contadorAnt.getEntrada().toString();
		salidaAnt = contadorAnt.getSalida().toString();
	}
	Contador contadorActual = maquinaBean.getContadorActual();
	String entrada = "";
	String salida = "";
	String comentarios = "";
	if (contadorActual != null){
		entrada = contadorActual.getEntrada().toString();
		salida = contadorActual.getSalida().toString();
		if (contadorActual.getComentarios() != null){
			comentarios = contadorActual.getComentarios().toString();
		}
	} else {
		entrada = entradaAnt;
		salida = salidaAnt;
	}
	String difEnt = "";
	String difSal = "";
	if (!entradaAnt.equals("") && !entrada.equals("")){
		BigInteger difEntrada = new BigInteger(entrada).subtract(new BigInteger(entradaAnt));
		difEnt = difEntrada.toString();
	}
	if (!salidaAnt.equals("") && !salida.equals("")){
		BigInteger difSalida = new BigInteger(salida).subtract(new BigInteger(salidaAnt));
		difSal = difSalida.toString();
	}
	Liquidacion liquidacionActual = maquinaBean.getLiquidacionActual();
	String bruto = "";
	String fallos = "";
	String recaudacion = "";
	String retencion = "";
	String porcentajeEst = maquinaBean.getPorcentajeEstablecimiento().setScale(2).toPlainString();
	String pagoEst = "";
	String neto = "";
	if (liquidacionActual != null){
		bruto = liquidacionActual.getBruto().setScale(2).toPlainString();
		if (liquidacionActual.getFallos() != null){
			fallos = liquidacionActual.getFallos().setScale(2).toPlainString();
		} else {
			fallos = "0,00";
		}
		recaudacion = liquidacionActual.getRecaudacion().setScale(2).toPlainString();
		retencion = liquidacionActual.getRetencion().setScale(2).toPlainString();
		if (liquidacionActual.getPorcentajeEst() != null){
			porcentajeEst = liquidacionActual.getPorcentajeEst().setScale(2).toPlainString();
		}
		pagoEst = liquidacionActual.getPagoEst().setScale(2).toPlainString();
		neto = liquidacionActual.getNeto().setScale(2).toPlainString();
	}
	BigDecimal retencionAcumuladaAnio = maquinaBean.getRetencionAcumuladaAnio();
	String acumulado = "0,00";
	if (retencionAcumuladaAnio != null){
		acumulado = retencionAcumuladaAnio.setScale(2).toPlainString();
	}
	boolean hayTicketActual = maquinaBean.isHayTicketActual();
	boolean puedeBorrar = contadorActual != null || liquidacionActual != null || hayTicketActual;
	Date fechaActual = DateUtil.getNow();
%>
			<div class="container-fluid">
			<div class="row" style="padding-top: 20px">
			</div>
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <strong>Establecimiento:</strong> <%=establecimiento.getNombre() %><br/>
                            <strong>Máquina:</strong> <%=maquina.getNombre() %>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <!-- Nav tabs -->
                            <ul class="nav nav-pills">
                                <li class="active"><a href="#contadores" data-toggle="tab">Contadores</a>
                                </li>
                                <li><a href="#liquidacion" data-toggle="tab">Liquidación</a>
                                </li>
                            </ul>

                            <!-- Tab panes -->
                            <div class="tab-content">
                                <div class="tab-pane fade in active" id="contadores">
                                    <div id="pestContadores" class="form-group">
                                    	<table class="pestCont">
                                    		<tr>
                                    			<td colspan="3" height="5">&nbsp;</td>
                                    		</tr>
											<tr>
                                    			<td colspan="3" align="left"><strong><%=DateUtil.getStringFromDate(fechaActual, "dd/MM/yyyy") %></strong></td>
                                    		</tr>
                                    		<tr>
                                    			<td>Entrada</td>
                                    			<td>
                                    				<label>Lec.Ant</label>
                                    				<input type="text" class="form-control" name="entradaAnt" id="entradaAnt" value="<%=entradaAnt %>" <% if (contadorAnt != null){ %>disabled <% } %>/>
                                    			</td>
                                    			<td>
                                    				<label>Lectura</label>
                                    				<input type="number" class="form-control input-number" onChange="calculaDiferencia('Entrada')" name="entrada" id="entrada" value="<%=entrada %>" />
                                    			</td>
                                    		</tr>
                                    		<tr>
                                    			<td>Salida</td>
                                    			<td>
                                    				<input type="text" class="form-control" name="salidaAnt" id="salidaAnt" value="<%=salidaAnt %>" <% if (contadorAnt != null){ %>disabled <% } %>/>
                                    			</td>
                                    			<td>
                                    				<input type="number" class="form-control input-number" onChange="calculaDiferencia('Salida')" name="salida" id="salida" value="<%=salida %>" />
                                    			</td>
                                    		</tr>
                                    		<tr>
                                    			<td>Diferencias</td>
                                    			<td>
                                    				<label>Dif.Entrada</label>
                                    				<input type="text" class="form-control" name="difEnt" id="difEnt" value="<%=difEnt %>" disabled />
                                    			</td>
                                    			<td>
                                    				<label>Dif.Salida</label>
                                    				<input type="number" class="form-control" name="difSal" id="difSal" value="<%=difSal %>" disabled />
                                    			</td>
                                    		</tr>
                                    		<tr>
                                    			<td colspan="3" height="15px;">&nbsp;</td>
                                    		</tr>
                                    		<tr>
                                    			<td colspan="3">
                                    				<label>Comentarios</label>
                                    				<textarea class="form-control" rows="3" name="comentarios" id="comentarios"><%=comentarios %></textarea>
                                    			</td>
                                    		</tr>
                                    		<tr>
                                    			<td colspan="3" align="right" style="padding-top:15px; padding-right:15px;">
                                    				<div style="float:left;">
                                    					<button type="button" id="delete1" onclick="DeleteLiquidacion(<%=maquina.getIdMaquina()%>);" class="btn btn-primary btn-xs" style="background-color:#FF0000;"<% if (!puedeBorrar){ %> disabled<% } %>>Borrar</button>
                                    				</div>
                                    				<button type="button" onClick="GotoEstablecimiento(<%=establecimiento.getIdEstablecimiento()%>)" class="btn btn-primary btn-xs">Volver</button>
                                    				<button type="button" onClick="SaveContadores(<%=maquina.getIdMaquina()%>)" class="btn btn-primary btn-xs">Guardar</button>
                                    			</td>
                                    		</tr>
                                    		<tr>
                                    			<td colspan="3" align="center" style="padding-top:15px;">
                                    				<div id="SaveResultContadores" class="resultHide">&nbsp;</div>
                                    			</td>
                                    		</tr>
                                    	</table>
                                    </div>
                                </div>
                                <div class="tab-pane fade" id="liquidacion">
                                    <div id="pestLiquidacion" class="form-group">
                                    	<table class="pestLiq">
                                    		<tr>
                                    			<td colspan="2" height="5">&nbsp;</td>
                                    		</tr>
                                    		<tr>
                                    			<td colspan="2" align="left"><strong><%=DateUtil.getStringFromDate(fechaActual, "dd/MM/yyyy") %></strong></td>
                                    		</tr>
                                    		<tr>
                                    			<td>Bruto</td>
                                    			<td>
                                    				<input type="number" step="0.01" class="form-control input-number" onChange="recalculaImportes(true)" name="bruto" id="bruto" value="<%=bruto %>" />
                                    			</td>
                                    		</tr>
                                    		<tr>
                                    			<td>Varios</td>
                                    			<td><input type="number" step="0.01" class="form-control input-number" onChange="cambioFallos()" name="fallos" id="fallos" value="<%=fallos %>" /></td>
                                    		</tr>
                                    		<tr>
                                    			<td colspan="2" height="20">&nbsp;</td>
                                    		</tr>
                                    		<tr>
                                    			<td>Recaudación</td>
                                    			<td><input type="number" step="0.01" class="form-control" name="recaudacion" id="recaudacion" value="<%=recaudacion %>" disabled /></td>
                                    		</tr>
                                    		<tr>
                                    			<td>Retención</td>
                                    			<td>
                                    				<input type="number" step="0.01" class="form-control input-number" onChange="recalculaImportes(false)" style="float:left;" name="retencion" id="retencion" value="<%=retencion %>" />
                                    				<a href="javascript:ShowRetencionTotal()"><i class="glyphicon glyphicon-eye-open" style="padding-left:5px;padding-top:8px;font-size: 16px;"></i></a>
                                    				<input type="hidden" name="acumulado" id="acumulado" value="<%=acumulado %>"/>
                                    			</td>
                                    		</tr>
                                    		<tr>
                                    			<td><input type="text" id="porcentajeEst" class="form-control" style="width:3em; padding:0px 0px 0px 3px; float:left;" value="<%=porcentajeEst %>" disabled /><div class="porcentaje">%Estab</div></td>
                                    			<td><input type="number" step="0.01" class="form-control input-number" name="pagoEst" id="pagoEst" value="<%=pagoEst %>" /></td>
                                    		</tr>
                                    		<tr>
                                    			<td>NETO</td>
                                    			<td><input type="number" step="0.01" class="form-control input-number" name="neto" id="neto" value="<%=neto %>" /></td>
                                    		</tr>
                                    		<tr>
                                    			<td colspan="2" height="15px;">&nbsp;</td>
                                    		</tr>
                                    		<tr>
                                    			<td colspan="2" align="right" style="padding-top:15px; padding-right:15px;">
                                    				<div style="float:left;">
                                    					<button type="button" id="delete2" onclick="DeleteLiquidacion(<%=maquina.getIdMaquina()%>);" class="btn btn-primary btn-xs" style="background-color:#FF0000;"<% if (!puedeBorrar){ %> disabled<% } %>>Borrar</button>
                                    				</div>
                                    				<button type="button" onClick="GotoEstablecimiento(<%=establecimiento.getIdEstablecimiento()%>)" class="btn btn-primary btn-xs">Volver</button>
                                    				<button type="button" onClick="SaveLiqMaquina(<%=maquina.getIdMaquina()%>)" class="btn btn-primary btn-xs">Guardar</button>
                                    			</td>
                                    		</tr>
                                    		<tr>
                                    			<td colspan="2" align="center" style="padding-top:15px; padding-right:15px;">
                                    				<div id="SaveResultLiquidacion" class="resultHide">&nbsp;</div>
                                    			</td>
                                    		</tr>
                                    	</table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            </div>