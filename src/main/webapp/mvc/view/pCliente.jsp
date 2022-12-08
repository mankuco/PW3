<%@ page import="business.Usuario.UsuarioDTO, business.Usuario.GestorUsuario, data.Reserva.ReservaDAO, java.util.ArrayList, java.time.format.DateTimeFormatter, java.time.LocalDate, java.time.temporal.ChronoUnit" %>
<jsp:useBean id="userBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	if (userBean.getEmail() == null){
		response.sendRedirect(request.getContextPath());
	}
	else if(userBean.getRol() == false){
		response.sendRedirect(request.getContextPath() + "/usuarioServlet");
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Bienvenido/a</title>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
	</head>
	<body>
		<%@ include file="../../include/headerCliente.jsp" %>
		<div class="container-users-main">
			<h1>Bienvenido/a</h1>
			<p><%= userBean.getNombre() + " " + userBean.getApellidos() %></p>
			<p>Fecha: <%= LocalDate.now() %></p>
			<% GestorUsuario gestor = new GestorUsuario(); %>
			<p>Antiguedad:  a&ntildeos</p>
			<% ReservaDAO reserva = new ReservaDAO(); %>
			<p>Fecha de la proxima reserva:</p>
		</div>
	</body>
</html>