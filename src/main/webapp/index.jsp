<jsp:useBean id="userBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<%
	if (userBean.getEmail() != null){
		if(userBean.getRol() == true){
			response.sendRedirect(request.getContextPath() + "/clienteServlet");
		}
		else{
			response.sendRedirect(request.getContextPath() + "/administradorServlet");
		}
	}
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<div>
<h2>Login</h2>
	<form>
		<form action="login.jsp" method="post">
			User: <input type="text" name="user" required="required">
			Contrasena: <input type="password" name="password" required="required">
			<input type="submit" value="Register">	
		</form>
	</form>
</div>
</body>
</html> 