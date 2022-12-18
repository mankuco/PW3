<%@ page import="business.Usuario.UsuarioDTO, data.Usuario.UsuarioDAO"%>
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
    <html lang="en">
        <head>
            <meta charset="ISO-8859-1">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
            <title>Nueva pista</title>
        </head>
        <body style="text-align: center">
        	<%@ include file="../../include/headerCliente.jsp" %>
            <h1>Crear Bono</h1>
            <form action="<%= request.getContextPath() %>/crearBono" method="POST">
                <br/>
                
                <label for="bonoType">Tipo</label>
                <select id="bonoType" class="select-type" name="bonoType">
                    <option value="INFANTIL">Infantil</option>
                    <option value="FAMILIAR">Familiar</option>
                    <option value="ADULTOS">Adultos</option>
                </select>
                
                <br/><br/>
                <input type="submit" class="small-button" value="Crear" style="border: solid 2px #cf74f2;">
             </form>
		</body>
	</html>
	<%} %>