<jsp:useBean id="userBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	if ((userBean.getEmail() != null) && (userBean.getRol() == true)){
		response.sendRedirect(request.getContextPath() + "/usuarioServlet");
	}
%>
<!DOCTYPE html>
<html lang="es">
    <head>
		<meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registro</title>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
    </head>
    <body style="text-align: center">
		<h1 style="text-align:center">Registro</h1>
		<% String msg = (String)request.getParameter("msg"); %>
    	<% if (msg != null) { %>
    		<p style="background-color: #ff9999; text-align: center;"><%= msg %></p>
    	<% } %>
	    <form action="../controller/registerController.jsp" method="POST">
            <label for="email">Email:</label>
            <input type="email" style="border: solid 2px #cf74f2;" class="input-form" id="email" name="email"><br/>
            
            <label for="contrasena">Contrase&ntilde;a:</label>
            <input type="password" style="border: solid 2px #cf74f2;" class="input-form" id="contrasena" name="contrasena">
            <br/>

            <label for="nombre">Nombre:</label>
            <input type="text" style="border: solid 2px #cf74f2;" class="input-form" id="nombre" name="nombre">
            <br/>

            <label for="apellidos">Apellidos:</label>
            <input type="text" style="border: solid 2px #cf74f2;" class="input-form" id="apellidos" name="apellidos">
            <br/>

            <label for="fechanacimiento">Fecha de nacimiento:</label>
            <input type="date" style="border: solid 2px #cf74f2;" class="input-form" id="fechanacimiento" name="fechanacimiento">
            <br/>
            <%if (userBean.getEmail() != null){ %>
            	<input type="submit" class="small-button" value="Registrarle" style="border: solid 2px #cf74f2;">
            <%} else { %>
            	<input type="submit" class="small-button" value="Registrarse" style="border: solid 2px #cf74f2;">
            <%} %>
		</form> 
    </body>