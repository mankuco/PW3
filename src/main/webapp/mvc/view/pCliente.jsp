<%@ page import="business.Usuario.UsuarioDTO, java.util.ArrayList, java.time.format.DateTimeFormatter, java.time.LocalDate, java.time.temporal.ChronoUnit" %>
<jsp:useBean id="userBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	if (userBean.getEmail() == null){
		response.sendRedirect(request.getContextPath());
	}
	else if(userBean.getRol() == true){
		response.sendRedirect(request.getContextPath() + "/clienteServlet");
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Bienvenido/a</title>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
	</head>
	<body>
		<%@ include file="../../include/headerCliente.jsp" %>
		<div class="container-users-main">
			<h1><%= userBean.getNombre() + " " + userBean.getApellidos() %></h1>
			<h1>Fecha: <%= LocalDate.now() %></h1>
			<h1>Antiguedad: <%= ChronoUnit.YEARS.between(userBean.getFechaInscripcion(), LocalDate.now())%></h1>
			<h1>Fecha de la proxima reserva:</h1>
		</div>
	</body>
</html>