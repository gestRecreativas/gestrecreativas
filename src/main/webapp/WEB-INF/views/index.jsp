<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.rms.recreativos.entity.Usuario" %>
<%@ page import="com.rms.recreativos.util.SessionUtil" %>    
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
        <jsp:include page="../components/menu.jsp" />
        <div id="page-wrapper">
        </div>
	</div>
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
</body>
</html>