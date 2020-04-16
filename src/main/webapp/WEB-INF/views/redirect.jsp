<%
	String url = (String) request.getAttribute("pagina");
	response.sendRedirect(url);
%>