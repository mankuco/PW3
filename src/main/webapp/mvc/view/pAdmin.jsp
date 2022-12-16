<%@ page import="business.Usuario.UsuarioDTO, business.Usuario.GestorUsuario, business.Reserva.GestorReservas, data.Reserva.ReservaDAO, java.util.ArrayList, java.time.format.DateTimeFormatter, java.time.LocalDate, java.time.temporal.ChronoUnit" %>
<jsp:useBean id="userBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	if (userBean.getEmail() == null){
		response.sendRedirect(request.getContextPath());
	}
	else if(userBean.getRol() == true){
		response.sendRedirect(request.getContextPath() + "/usuarioServlet");
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Usuarios</title>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
	</head>
	<body>
		<%@ include file="../../include/headerAdministrador.jsp" %>
		<% ArrayList<UsuarioDTO> usuarios = (ArrayList<UsuarioDTO>) request.getAttribute("listaUsuarios"); %>
		<% GestorUsuario gestorU = new GestorUsuario(); %>
		<% GestorReservas gestorR = new GestorReservas(); %>
		<h1 style="text-align:center">Usuarios</h1>
		<% if (usuarios == null || usuarios.size() == 0) { %>
		
			<h2 style="text-align:center">No hay usuarios registrados</h2>
			
		<% } else { %>
		
			<table width="1000" height="200" border="3" bordercolor=rgb(106,0,106) style="margin: 0 auto;">
				<tr style="text-align:center">
					<td>Nombre</td>
					<td>Apellidos</td>
					<td>Email</td>
					<td>Antiguedad (en a&ntilde;os)</td>
					<td>Nº Reservas Completadas</td>
				</tr>
				<% ServletContext context = getServletContext(); %>
				<% for (UsuarioDTO a : usuarios){ %>
					<tr>
						<td><%= a.getNombre() %></td>
						<td><%= a.getApellidos() %></td>
						<td><%= a.getEmail() %></td>
						<td style="text-align:center"><%= gestorU.CalcularAntiguedad(a.getEmail(), userBean.getprop(), userBean.getjdbc(), userBean.getdbuser(), userBean.getdbpass())%></td>
						<td style="text-align:center"><%= gestorR.reservasCompletadas(a.getEmail(), userBean.getprop(), userBean.getjdbc(), userBean.getdbuser(), userBean.getdbpass())%></td>
					</tr>
				<% } %>
			</table>
			
		<% } %>
	</body>
</html>
