<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld" %>
<%@ page import="com.rms.recreativos.entity.Maquina" %>
<%@ page import="com.rms.recreativos.entity.Establecimiento" %>
<%@ page import="com.rms.recreativos.entity.TipoMaquina" %>
<%@ page import="com.rms.recreativos.entity.EmpresaMaquina" %>
<%@ page import="com.rms.recreativos.service.CreateOrUpdateMaquinaBean" %>
<%@ page import="com.rms.recreativos.util.ListUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%
	CreateOrUpdateMaquinaBean createOrUpdateMaquinaBean = (CreateOrUpdateMaquinaBean) request.getAttribute("createOrUpdateMaquinaBean");
	Maquina maquina = createOrUpdateMaquinaBean.getMaquina();
	List<TipoMaquina> tiposMaquina = createOrUpdateMaquinaBean.getTiposMaquina();
	List<EmpresaMaquina> empresasMaquina = createOrUpdateMaquinaBean.getEmpresasMaquina();
	
	String idMaquina = "";
	String nombre = "";
	String codigo = "";
	String porcentajeEst = "0";
	String cargas = "0";
	String idTipoMaquina = "";
	String idEmpresaMaquina = "";
	String idEstablecimiento = "";
	if (maquina != null){
		idMaquina = maquina.getIdMaquina().toString();
		nombre = maquina.getNombre();
		codigo = maquina.getCodigoMaquina();
		if (maquina.getPorcentajeEstablecimiento() != null){
			porcentajeEst = maquina.getPorcentajeEstablecimiento().setScale(2).toPlainString();
		}
		if (maquina.getCargas() != null){
			cargas = maquina.getCargas().setScale(2).toPlainString();
		}
		if (maquina.getIdTipoMaquina() != null){
			idTipoMaquina = maquina.getIdTipoMaquina().toString();
		}
		if (maquina.getIdEmpresaMaquina() != null){
			idEmpresaMaquina = maquina.getIdEmpresaMaquina().toString();
		}
		if (maquina.getIdEstablecimiento() != null){
			idEstablecimiento = maquina.getIdEstablecimiento().toString();
		}
	}
%>

			<div class="container-fluid">
			<div class="row" style="padding-top: 20px">
			</div>
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <strong>
                            <% if (maquina == null){ %>
                            	Creación de Máquina
                            <% } else { %>
                            	Edición de Máquina
                            <% } %>
                            </strong>
                        </div>
                        <div class="panel-body">
                            <table cellpadding="0" cellspacing="0" border="0">
                            	<tr>
                            		<td>Nombre</td>
                            		<td>
                            			<input type="text" class="form-control" name="nombre" id="nombre" value="<%=nombre %>" />
                            		</td>
                            	</tr>
                            	<tr>
                            		<td>Código</td>
                            		<td>
                            			<input type="text" class="form-control" name="codigo" id="codigo" value="<%=codigo %>" />
                            		</td>
                            	</tr>
                            	<tr>
                            		<td>Porcentaje Establecimiento</td>
                            		<td>
                            			<input type="number" step="0.01" class="form-control input-number" name="porcentajeEst" id="porcentajeEst" value="<%=porcentajeEst %>" />
                            		</td>
                            	</tr>
                            	<tr>
                            		<td>Arqueo</td>
                            		<td>
                            			<input type="number" step="0.01" class="form-control input-number" name="cargas" id="cargas" value="<%=cargas %>" />
                            		</td>
                            	</tr>
                            	<tr>
                            		<td>Tipo de Máquina</td>
                            		<td>
                            			<select name="tipoMaquina" id="tipoMaquina" class="form-control">
                            				<option value="">Selecciona</option>
                            				<% if (!ListUtil.nullOrEmptyList(tiposMaquina)){
                            						Iterator<TipoMaquina> it = tiposMaquina.iterator();
                            						TipoMaquina tipoMaquina = null;
                            						while (it.hasNext()){
                            							tipoMaquina = it.next();
                            				%>
                            				<option value="<%= tipoMaquina.getIdTipoMaquina()%>"><%= tipoMaquina.getTipo()%></option>
                            				<%
                            						}
                            				 	}
                            				%>
                            			</select>
                            			<input type="hidden" name="idTipoMaquina" id="idTipoMaquina" value="<%=idTipoMaquina %>" />
                            		</td>
                            	</tr>
                            	<tr>
                            		<td>Empresa Máquina</td>
                            		<td>
                            			<select name="empresaMaquina" id="empresaMaquina" class="form-control" onChange="CambiaEmpresaMaquina()">
                            				<option value="">Selecciona</option>
                            				<% if (!ListUtil.nullOrEmptyList(empresasMaquina)){
                            						Iterator<EmpresaMaquina> it = empresasMaquina.iterator();
                            						EmpresaMaquina empresaMaquina = null;
                            						while (it.hasNext()){
                            							empresaMaquina = it.next();
                            				%>
                            				<option value="<%= empresaMaquina.getIdEmpresaMaquina()%>"><%= empresaMaquina.getRazonSocial()%></option>
                            				<%
                            						}
                            				 	}
                            				%>
                            			</select>
                            			<input type="hidden" name="idEmpresaMaquina" id="idEmpresaMaquina" value="<%=idEmpresaMaquina %>" />
                            		</td>
                            	</tr>
                            	<tr>
                            		<td>Establecimiento</td>
                            		<td>
                            			<select name="establecimiento" id="establecimiento" class="form-control">
                            				<option value="">Selecciona</option>
                            			</select>
                            			<input type="hidden" name="idEstablecimiento" id="idEstablecimiento" value="<%=idEstablecimiento %>" />
                            		</td>
                            	</tr>
                            	<tr>
                            		<td colspan="2" align="right" style="padding-top:15px; padding-right:15px;">
                            			<a href="adminMaquinas.html" class="btn btn-primary btn-xs">Volver</a>
                            			<button type="button" onClick="CreateOrUpdateMaquina(<%=idMaquina%>)" class="btn btn-primary btn-xs">Guardar</button>
                            		</td>
                            	</tr>
                            	<tr>
                            		<td colspan="2" align="center" style="padding-top:15px;">
                            			<div id="SaveResultAdminMaquina" class="resultHide">&nbsp;</div>
                            		</td>
                            	</tr>
                            </table>
	                    </div>
	                </div>
	            </div>
            </div>
            </div>