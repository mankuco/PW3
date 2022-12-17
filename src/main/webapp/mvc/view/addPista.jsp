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
    <html lang="en">
        <head>
            <meta charset="ISO-8859-1">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
            <title>Nueva pista</title>
        </head>
        <body>
        <%@ include file="../../include/headerAdministrador.jsp" %>
            <div class="container">
                <h1>Crear pista</h1>
                <form>
                    <br/>
                    <label for="pistaName">Nombre</label>
                    <input type="text" class="input-form" name="pistaName">
                    <label for="maxKarts">Numero de karts maximo</label>
                    <input type="text" class="input-form" name="maxKarts">
                    <br/>
                    <label for="pistaType">Tipo</label>
                    <select id="pistaType" class="select-type" name="pistaType">
                        <option value="">INFANTIL</option>
                        <option value="">FAMILIAR</option>
                        <option value="">ADULTOS</option>
                    </select>
                    <br/>
                    </div>
                  </form> 
                </body>
		</html>