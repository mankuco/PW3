<%@ page import="business.Usuario.UsuarioDTO, business.Usuario.GestorUsuario, business.Reserva.GestorReservas, data.Reserva.ReservaDAO, java.util.ArrayList, java.time.format.DateTimeFormatter, java.time.LocalDate, java.time.temporal.ChronoUnit" %>
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
			<h1 style="text-align:center">Bienvenido/a</h1>
			<h3 style="margin-left:20px">Nombre y Apellidos: <%= userBean.getNombre() + " " + userBean.getApellidos() %></h3>
			<h3 style="margin-left:20px">Fecha: <%= LocalDate.now() %> </h3>
			<% GestorUsuario gestor = new GestorUsuario(); %>
			<h3 style="margin-left:20px">Antiguedad: <%= gestor.CalcularAntiguedad(userBean.getEmail()) %> a&ntildeos</h3>
			<% GestorReservas gest = new GestorReservas(); %>
			<h3 style="margin-left:20px">Pr&oacutexima reserva: <%= gest.proximaReserva(userBean.getEmail()) %></h3>
	</body>
</html>