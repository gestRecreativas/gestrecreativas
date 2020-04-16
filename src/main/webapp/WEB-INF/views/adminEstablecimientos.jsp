<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld" %>
<%@ page import="com.rms.recreativos.entity.Usuario" %>
<%@ page import="com.rms.recreativos.util.SessionUtil" %>
<%@ page import="com.rms.recreativos.util.StringUtil" %>
<%
	Usuario usuario = SessionUtil.getUsuario(session);
	
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
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Administrar Establecimiento
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-adminEstablecimientos">
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
            <div class="row">
            	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="text-align:center;">
            		<button type="button" onClick="GotoCreateUpdateEstablecimiento()" class="btn btn-primary btn-xs" style="margin-bottom:10px;">Crear nuevo establecimiento</button>
            	</div>
            </div>
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
<script src="./js/data.js" charset="UTF-8"></script>
<script src="./js/utils.js"></script>
<script src="./js/services.js"></script>
<script>
$(function() {
	if ($("#dataTables-adminEstablecimientos") != undefined){
		$.get(uriAdminEstablecimientos, function( data ) {
			var object = data;
			generateRowsTableAdminEstablecimientos(data);
			FullTable('#dataTables-adminEstablecimientos', columnSetAdminEstablecimientos, true);
		})
		.fail(function(err) {
			getCapaError(errorMessage);
			console.log("Error en la llamada: " + err);		
		});
	}
});
</script>
</body>
</html>