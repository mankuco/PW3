<%@ page import="business.Usuario.UsuarioDTO, java.util.ArrayList, java.time.format.DateTimeFormatter, java.time.LocalDate, java.time.temporal.ChronoUnit" %>
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
		<div class="container-users-main">
			<h1>Usuarios</h1>
			<% if (usuarios == null || usuarios.size() == 0) { %>
				<p>No hay usuarios registrados</p>
			<% } else { %>
				<div class="usuarios">
					<table>
						<tr>
							<td>Nombre</td>
							<td>Apellidos</td>
							<td>Email</td>
							<td>Antiguedad</td>
							<td>Nº Reservas Completadas</td>
						</tr>
						<% for (UsuarioDTO a : usuarios){ %>
							<tr>
								<td><%= a.getNombre() %></td>
								<td><%= a.getApellidos() %></td>
								<td><%= a.getEmail() %></td>
								<td><%= ChronoUnit.YEARS.between(a.getFechaInscripcion(), LocalDate.now())%></td>
								<td> POR HACER </td>
							</tr>
						<% } %>
					</table>
				</div>
			<% } %>
		</div>
	</body>
</html>