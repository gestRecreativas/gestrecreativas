<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>    

<!DOCTYPE html>
<html lang="en">

<head>
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <link rel="manifest" href="manifest.json">
	<link rel="icon" sizes="192x192" href="./img/tragaperras_icon.svg">
	<link rel="apple-touch-icon" href="./img/tragaperras_icon.svg">

    <!-- Bootstrap CSS -->
    <link href="./css/bootstrap.min.css" rel="stylesheet">
	<link href="./css/login.css" rel="stylesheet">
	<title>Montamaquinas</title>
	<script type="text/javascript">
		function deshabilitaRetroceso(){
			window.location.hash="no-back-button";
			window.location.hash="Again-No-back-button";
			window.onhashchange=function(){window.location.hash="no-back-button";}
		}
	</script>
</head>
<script type="text/javascript" src="./js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="./js/popper.min.js"></script>
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<body onLoad="deshabilitaRetroceso();" class="text-center">
    <form class="form-login" action="loginAction.html">
      <img class="mb-4" src="./img/tragaperras_icon.svg" alt="" width="72" height="72">
      <h1 class="h4 mb-3 font-weight-normal">Por favor, rellene los datos</h1>
	  <label for="us" class="sr-only">Usuario</label>
      <input name="us" type="text" class="form-control" placeholder="Usuario" required autofocus/>
      <label for="pwd" class="sr-only">Contraseña</label>
      <input name="pwd" type="password" class="form-control" placeholder="Contraseña" required/>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
    </form>
</body>
</html>