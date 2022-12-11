<jsp:useBean id="userBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	if (userBean.getEmail() != null){
		response.sendRedirect(request.getContextPath() + "/usuarioServlet");
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css">
    <title>Iniciar sesion</title>
</head>
<body>
	<div class="cuadro">
		<div class="container-center">
			<div class="header">
				<h1 class="page-title">Iniciar sesi&oacute;n</h1>
				<p class="subtitle">Introduzca su email y contrase&ntilde;a</p>
		    </div>
	    	<% if (request.getParameter("msg") != null) { %>
	    		<p style="background-color: #ff9999; text-align: center;"><%= request.getParameter("msg") %></p>
	    	<% } %>
	        <form action="../controller/loginController.jsp" method="post" style="text-align: center">
	            <label for="email">Email:</label>
	            <input type="text" class="input-form" name="email">
	            <br/>
	            <label for="contrasena">Contrase&ntilde;a:</label>
	            <input type="password" class="input-form" name="contrasena">
	            <br/>
	            <input type="submit" class="small-button" id="button-submit-login" value="Iniciar sesión">
	        </form>
		</div>
	</div>
</body>
</html>