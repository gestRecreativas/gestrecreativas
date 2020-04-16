<%@page contentType="text/plain" %>
<%
java.io.File fichero = (java.io.File) request.getAttribute("ficheroTxt");
response.setHeader("Content-disposition","attachment;filename=" + fichero.getName());
StringBuffer sb = new StringBuffer();
String line = null;
try {
	java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(fichero));
	while ((line = br.readLine()) != null) {
		sb.append(line);
		sb.append("\n");
	}
	br.close();
} catch (Exception e){
    System.out.println("No ha sido posible encontrar el archivo");
}
%>
<%=sb.toString()%>