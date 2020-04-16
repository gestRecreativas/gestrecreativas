<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.rms.recreativos.entity.Usuario" %>
<%@ page import="com.rms.recreativos.service.RecaudacionesBean" %>
<%@ page import="com.rms.recreativos.service.RecaudacionBean" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.rms.recreativos.util.SessionUtil" %>
<%@ page import="com.rms.recreativos.util.DateUtil" %>
<%
	Usuario usuario = SessionUtil.getUsuario(session);
	RecaudacionesBean recaudacionesBean = (RecaudacionesBean) request.getAttribute("recaudacionesBean");
	List<RecaudacionBean> recaudaciones = recaudacionesBean.getRecaudaciones();
	Date fecha = recaudacionesBean.getFecha();
	String fechaStr = "";
	if (fecha != null){
		fechaStr = DateUtil.getStringFromDate(fecha, "dd-MM-yyyy");
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="../components/headerTags.jsp" />
	<script type="text/javascript">
		var idUsuario = <%= usuario.getIdUsuario() %>;
		function deshabilitaRetroceso(){
			window.location.hash="no-back-button";
			window.location.hash="Again-No-back-button";
			window.onhashchange=function(){window.location.hash="no-back-button";}
		}
	</script>
</head>
<body onLoad="deshabilitaRetroceso();">
	<div id="wrapper">
		<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
			<div class="CabeceraLogo">
				GestRecreativos 1.0
			</div>
			<ul class="nav navbar-top-links navbar-right">
			    <li class="dropdown">
			        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
			            <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
			        </a>
			        <ul class="dropdown-menu dropdown-user">
			            <li id="UserName"><i class="fa fa-user fa-fw"></i><%= usuario.getLogin() %></li>
			            <li class="divider"></li>
			            <li>
			            	<a href="logout.html">
			            		<i class="fa fa-sign-out fa-fw"></i>Desconectar
			            	</a>
			            </li>
			        </ul>
			    </li>
			    <% if (SessionUtil.isAdmin(session)){ %>
				<li class="dropdown">
			        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
			            <i class="fa fa-gear fa-fw"></i> <i class="fa fa-caret-down"></i>
			        </a>
			        <ul class="dropdown-menu">
			            <li>
			            	<a href="adminMaquinas.html">
			            		<i class="fa fa-gear fa-fw"></i>Administrar Máquinas
			            	</a>
			            </li>
			            <li>
			            	<a href="adminEstablecimientos.html">
			            		<i class="fa fa-gear fa-fw"></i>Administrar Establecimientos
			            	</a>
			            </li>
			        </ul>
			    </li>
				<% } %>
				<li class="dropdown">
					<a class="dropdown-toggle" href="index.html">
			            <i class="glyphicon glyphicon-home"></i>
			        </a>
				</li>
				<li class="dropdown">
					<a class="dropdown-toggle" href="establecimientos.html?verTodos=1">
			            <i class="glyphicon glyphicon-list"></i>
			        </a>
				</li>
				<li class="dropdown">
					<a class="dropdown-toggle" href="recaudaciones.html">
			            <i class="glyphicon glyphicon-eur"></i>
			        </a>
				</li>
			</ul>
		</nav>
		<div id="page-wrapper">
            <div class="container-fluid">
			<div class="row" style="padding-top: 20px">
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<form id="formBuscar" name="formBuscar" method="post" action="recaudaciones.html">
					<div class="input-group date">
    					<label>Fecha:</label><input type="text" class="input-small form-control" id ="fecha" name="fecha" placeholder="dd-mm-yyyy" value="<%= fechaStr %>"/>
					</div>
					<div style="float:right; margin-right:10px;">
						<button type="button" id="botonBuscar" class="btn btn-primary btn-xs">Buscar</button>
					</div>
					</form>
				</div>
			</div>
			<div class="row" style="padding-top: 20px">
			</div>
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Recaudaciones
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-recaudaciones">
                            	<thead>
                            		<tr>
                            			
                            			<th>Establecimiento</th>
                            			<th>Fecha</th>
                            			<th>Usuario</th>                            			
                            			<th></th>
                            			<th>Empresa</th>
                            		</tr>
                            	</thead>
                            	<tbody>
                     	<%
                     		RecaudacionBean recaudacionBean = null;
                     		Iterator<RecaudacionBean> it = recaudaciones.iterator();
                     		while (it.hasNext()){
                     			recaudacionBean = it.next();
                     	%>
                            		<tr>
                            			
                            			<td><%= recaudacionBean.getEstablecimiento() %></td>
                            			<td><%= DateUtil.getStringFromDate(recaudacionBean.getFecha(), "dd-MM-yyyy")%>&nbsp;<%=recaudacionBean.getHora()  %></td>
                            			<td><%= recaudacionBean.getLogin() %></td>                            			
                            			<td>
                            				<a href="javascript:VerTicketRecaudacion(<%= recaudacionBean.getIdEstablecimiento() %>, <%= recaudacionBean.getIdTicket() %>)"><i class="glyphicon glyphicon-eye-open" style="padding-left:5px;padding-top:8px;font-size: 16px;"></i></a>
                            			</td>
                            			<td><%= recaudacionBean.getEmpresaMaquina() %></td>
                            		</tr>
                    	<%
                    		}
                    	%>
                            	</tbody>
                            </table>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            </div>
		</div>
	</div>
<script src="./js/cpgadget.js"></script>
<script src="./vendor/jquery/jquery.min.js"></script>
<script src="./vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="./vendor/metisMenu/metisMenu.min.js"></script>
<script src="./vendor/datatables/js/jquery.dataTables.min.js"></script>
<script src="./vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
<script src="./vendor/datatables-responsive/dataTables.responsive.js"></script>
<script src="./js/sb-admin-2.js"></script>
<script src="./js/bootstrap-datepicker.min.js" charset="UTF-8"></script>
<script src="./js/bootstrap-datepicker.es.min.js" charset="UTF-8"></script>
<script src="./js/data.js" charset="UTF-8"></script>
<script src="./js/utils.js"></script>
<script src="./js/services.js"></script>
<script>
$('#fecha').datepicker({
    format: "dd-mm-yyyy",
    clearBtn: true,
    language: "es",
    todayHighlight: true
});
$('#fecha').on('changeDate', function(ev){
	$(this).datepicker('hide');
});
$('#botonBuscar').click(function(){
	$('#formBuscar').submit();
});
var textos = (function () {
    var json = null;
    var urilang = "./js/datatable.json";
    $.ajax({
        'async': false,
        'global': false,
        'url': urilang,
        'dataType': "json",
        'success': function (data) {
            json = data;
        }
    });
    return json;
})();
$('#dataTables-recaudaciones').DataTable( {
	responsive: true,
	lengthMenu: [[ 25, 50, 100], [25, 50, 100]],
	order: [[ 0, "asc" ]],
	language: textos
});
</script>
</body>
</html>