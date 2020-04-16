<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld" %>
<%@ page import="com.rms.recreativos.entity.Establecimiento" %>
<%@ page import="com.rms.recreativos.entity.EmpresaMaquina" %>
<%@ page import="com.rms.recreativos.service.CreateOrUpdateEstablecimientoBean" %>
<%@ page import="com.rms.recreativos.util.ListUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%
	CreateOrUpdateEstablecimientoBean createOrUpdateEstablecimientoBean = (CreateOrUpdateEstablecimientoBean) request.getAttribute("createOrUpdateEstablecimientoBean");
	Establecimiento establecimiento = createOrUpdateEstablecimientoBean.getEstablecimiento();
	List<EmpresaMaquina> empresasMaquina = createOrUpdateEstablecimientoBean.getEmpresasMaquina();
	boolean tieneMaquinas = createOrUpdateEstablecimientoBean.getTieneMaquinas();
	
	String idEstablecimiento = "";
	String nombre = "";
	String codigoEstablecimiento = "";
	String email = "";
	String bote = "0";
	String idEmpresaMaquina = "";
	if (establecimiento != null){
		idEstablecimiento = establecimiento.getIdEstablecimiento().toString();
		nombre = establecimiento.getNombre();
		codigoEstablecimiento = establecimiento.getCodigoEstablecimiento();
		email = establecimiento.getEmail();
		if (establecimiento.getBote() != null){
			bote = establecimiento.getBote().setScale(2).toPlainString();
		}
		if (establecimiento.getIdEmpresaMaquina() != null){
			idEmpresaMaquina = establecimiento.getIdEmpresaMaquina().toString();
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
                            <% if (establecimiento == null){ %>
                            	Creación de Establecimiento
                            <% } else { %>
                            	Edición de Establecimiento
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
                            			<input type="text" class="form-control" name="codigoEstablecimiento" id="codigoEstablecimiento" value="<%=codigoEstablecimiento %>" />
                            		</td>
                            	</tr>
                            	<tr>
                            		<td>Email</td>
                            		<td>
                            			<input type="text" class="form-control" name="email" id="email" value="<%=email %>" />
                            		</td>
                            	</tr>
                            	<tr>
                            		<td>Bote</td>
                            		<td>
                            			<input type="number" step="0.01" class="form-control input-number" name="bote" id="bote" value="<%=bote %>" />
                            		</td>
                            	</tr>
                            	<tr>
                            		<td>Empresa Máquina</td>
                            		<td>
                            			<select name="empresaMaquina" id="empresaMaquina" class="form-control" <% if (tieneMaquinas){ %>disabled<% } %>>
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
                            		<td colspan="2" align="right" style="padding-top:15px; padding-right:15px;">
                            			<a href="adminEstablecimientos.html" class="btn btn-primary btn-xs">Volver</a>
                            			<button type="button" onClick="CreateOrUpdateEstablecimiento(<%=idEstablecimiento%>)" class="btn btn-primary btn-xs">Guardar</button>
                            		</td>
                            	</tr>
                            	<tr>
                            		<td colspan="2" align="center" style="padding-top:15px;">
                            			<div id="SaveResultAdminEstablecimiento" class="resultHide">&nbsp;</div>
                            		</td>
                            	</tr>
                            </table>
	                    </div>
	                </div>
	            </div>
            </div>
            </div>