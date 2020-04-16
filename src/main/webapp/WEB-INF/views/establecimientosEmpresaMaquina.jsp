<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.rms.recreativos.entity.Establecimiento" %>
<%@ page import="com.rms.recreativos.util.ListUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%
	List<Establecimiento> establecimientos = (List<Establecimiento>) request.getAttribute("establecimientos");
	String opciones = "";
	if (!ListUtil.nullOrEmptyList(establecimientos)){
		Iterator<Establecimiento> it = establecimientos.iterator();
		Establecimiento establecimiento = null;
		while (it.hasNext()){
			establecimiento = it.next();
			opciones += "<option value='" + establecimiento.getIdEstablecimiento() + "'>" + establecimiento.getNombre() + "</option>";
		}
	}
%>
<option value=''>Selecciona</option><%=opciones%>