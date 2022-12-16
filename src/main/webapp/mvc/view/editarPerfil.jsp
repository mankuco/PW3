<%@ page import="business.Usuario.UsuarioDTO, data.Usuario.UsuarioDAO"%>
<jsp:useBean id="userBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	if (userBean.getEmail() == null){
		response.sendRedirect(request.getContextPath());
	}
	else {
%>
<!DOCTYPE html>
<html>
	<head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
        <title>Editar usuario</title>
	</head>
	<body>
		<%if (userBean.getRol() == true){ %>
			<%@ include file="../../include/headerCliente.jsp" %>
		<%} else {%>
			<%@ include file="../../include/headerAdministrador.jsp" %>
		<%} %>
		
		<h1 style="text-align:center">Modificar perfil</h1>
		<h2 style="text-align:center"><%= userBean.getEmail() %></h2>
		
		<% String msg = (String)request.getParameter("msg"); %>
    	<% if (msg != null) { 
    			if (msg.equals("Campos incompletos")){%>
    				<p style="background-color: #ff9999; text-align: center;"><%= msg %></p>
    	<% 		} else { %>
    				<p style="background-color: #abedb2; text-align: center;"><%= msg %></p>
    	<% } } %>
	    <form class="modify-form" action="/PW3/mvc/controller/modifyController.jsp" method="POST" style="text-align: center">
            <label for="nombre">Nombre:</label>
            <input type="text" style="border: solid 2px #cf74f2;" class="input-form" id="name" name="nombre" value="<%= userBean.getNombre() %>">
            <br/>

            <label for="apellidos">Apellidos:</label>
            <input type="text" style="border: solid 2px #cf74f2;" class="input-form" id="lastname" name="apellidos" value="<%= userBean.getApellidos() %>">
            <br/>

			<%UsuarioDAO user = new UsuarioDAO(userBean.getprop(), userBean.getjdbc(), userBean.getdbuser(), userBean.getdbpass()); %>
			<%UsuarioDTO usuario = user.buscarUsuario(userBean.getEmail()); %>
            <label for="fechaNacimiento">Fecha de nacimiento:</label>
            <input type="date" style="border: solid 2px #cf74f2;" class="input-form" id="birthdate" name="fechaNacimiento" value="<%= usuario.getFechaNacimiento() %>">
            <br/>

            <input type="submit" class="small-button" value="Modificar" style="border: solid 2px #cf74f2;">
		</form>
		<div style="text-align: center">
			<button type="button" class="small-button" style="width: 200px; border: solid 2px #cf74f2;" onclick="window.location.href='<%= request.getContextPath() %>/mvc/view/cambiarContrasena.jsp'">Cambiar contrase&ntilde;a</button>
			<br/><br/>
			<%if(userBean.getRol() == false){ %>
				<button type="button" class="big-button" style="width: 300px; border: solid 2px #cf74f2;" onclick="window.location.href='<%= request.getContextPath() %>/mvc/view/registerView.jsp'">A&ntilde;adir nuevo administrador</button>
			<%} }%>
		</div>
	</body>
</html>