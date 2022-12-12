<%@ page import="business.Usuario.UsuarioDTO, data.Usuario.UsuarioDAO"%>
<jsp:useBean id="userBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	if (userBean.getEmail() == null){
		response.sendRedirect(request.getContextPath());
	}
%>
<!DOCTYPE html>
<html>
	<head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
        <title>Editar contrasena</title>
	</head>
	<body>
		<%if (userBean.getRol() == true){ %>
			<%@ include file="../../include/headerCliente.jsp" %>
		<%} else {%>
			<%@ include file="../../include/headerAdministrador.jsp" %>
		<%} %>
		
		<h1 style="text-align:center">Modificar contrase&ntilde;a</h1>
		<h2 style="text-align:center"><%= userBean.getEmail() %></h2>
		
		<% String msg = (String)request.getParameter("msg"); %>
    	<% if (msg != null) { %>
    		<p style="background-color: #ff9999; text-align: center;"><%= msg %></p>
    	<% } %>
		<div style="text-align: center">
			<form action="/PW3/mvc/controller/contrasenaController.jsp" method="POST">
				<label for="contrasena">Contrase&ntilde;a actual</label>
				<input type="password" class="input-form" name="contrasena">
				<br/>
				<label for="nueva">Nueva contrase&ntilde;a</label>
				<input type="password" class="input-form" name="nueva">
				<br/>
				<input type="submit" style="width: 200px; border: solid 2px #cf74f2;" class="small-button" id="change-password-btn" value="Cambiar contrase&ntilde;a">
			</form>
		</div>
	</body>
</html>