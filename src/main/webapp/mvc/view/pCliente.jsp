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
	else {
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
		<p style="margin-left:20px; font-size: 16px;">Nombre y Apellidos: <%= userBean.getNombre() + " " + userBean.getApellidos() %></p>
		<p style="margin-left:20px; font-size: 16px;">Fecha: <%= LocalDate.now() %> </p>
		<% GestorUsuario gestor = new GestorUsuario(); %>
		<p style="margin-left:20px; font-size: 16px;">Antiguedad: <%= gestor.CalcularAntiguedad(userBean.getEmail()) %> a&ntilde;os</p>
		<% GestorReservas gest = new GestorReservas(); %>
		<p style="margin-left:20px; font-size: 16px;">Pr&oacute;xima reserva: <%= gest.proximaReserva(userBean.getEmail()) %></p>
		<%} %>
	</body>
</html>