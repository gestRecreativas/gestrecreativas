<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld" %>
<%@ page import="com.rms.recreativos.entity.Usuario" %>
<%@ page import="com.rms.recreativos.util.SessionUtil" %>    
<%
	Usuario usuario = SessionUtil.getUsuario(session);
%>
	<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
		<div class="CabeceraLogo">
			Montamaquinas 1.0
		</div>
		<div class="navbar-header">
		    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		    </button>            
		    <a class="navbar-brand" href="index.html">Montamaquinas</a>
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
		</ul>
		<div class="navbar-default sidebar" role="navigation">
		    <div class="sidebar-nav navbar-collapse">
		        <ul class="nav" id="side-menu">
		        	<li>
		                <a href="establecimientos.html?verTodos=1"><i class="glyphicon glyphicon-list"></i>&nbsp;ESTABLECIMIENTOS</a>
		            </li>
		            <li>
		                <a href="recaudaciones.html"><i class="glyphicon glyphicon-eur"></i>&nbsp;RECAUDACIONES</a>
		            </li>
		            <% if (SessionUtil.isAdmin(session)){ %>
		            <li>
		                <a href="#"><i class="fa fa-gear fa-fw"></i>ADMINISTRACIÓN<span class="fa arrow"></span></a>
		                <ul class="nav nav-second-level">
		                    <li>
		                        <a href="adminMaquinas.html">Administrar Máquinas</a>
		                    </li>
		                    <li>
		                        <a href="adminEstablecimientos.html">Administrar Establecimientos</a>
		                    </li>
		                </ul>
		            </li>
		            <% } %>
		        </ul>
		    </div>
		</div>
	</nav>