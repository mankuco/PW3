<%@ page import="business.Usuario.UsuarioDTO, data.Usuario.UsuarioDAO"%>
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
	else {
%>
    <!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="ISO-8859-1">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
            <title>Nueva pista</title>
        </head>
        <body style="text-align: center">
        	<%@ include file="../../include/headerAdministrador.jsp" %>
            <h1>Crear pista</h1>
            <form action="<%= request.getContextPath() %>/nuevaPista" method="POST">
                <br/>
                <label for="pistaName">Nombre</label>
                <input type="text" class="input-form" name="pistaName">
                
                <br/>
                
                <label for="maxKarts">Numero de karts maximo</label>
                <input type="number" class="input-form" name="maxKarts">
                
                <br/><br/>
                <label for="pistaType">Tipo</label>
                <select id="pistaType" class="select-type" name="pistaType">
                    <option value="">INFANTIL</option>
                    <option value="">FAMILIAR</option>
                    <option value="">ADULTOS</option>
                </select>
                
                <br/><br/>
                <input type="submit" class="small-button" value="Crear" style="border: solid 2px #cf74f2;">
             </form>
		</body>
	</html>
	<%} %>