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
		String id = request.getParameter("nPista");
%>
    <!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="ISO-8859-1">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
            <title>Editar Pista</title>
        </head>
        <body style="text-align: center">
        	<%@ include file="../../include/headerAdministrador.jsp" %>
            <h1>Editar pista</h1>
            <form action="<%= request.getContextPath() %>/editarPistaServlet" method="POST">
                <br/>
                <label for="pistaName">Nombre</label>
                <input type="text" class="input-form" name="pistaName" value=<%=id%>>
                
                <br/>
                
                <label for="maxKarts">Numero de karts maximo</label>
                <input type="number" class="input-form" name="maxKarts">
                
                <br/><br/>
                <label for="pistaType">Tipo</label>
                <select id="pistaType" class="select-type" name="pistaType">
                    <option value="INFANTIL">Infantil</option>
                    <option value="FAMILIAR">Familiar</option>
                    <option value="ADULTOS">Adultos</option>
                </select>
                <select id="pistaEstado" class="select-type" name="pistaEstado">
                    <option value="true">Disponible</option>
                    <option value="false">Mantenimiento</option>
                </select>
                
                <br/><br/>
                <input type="submit" class="small-button" value="Modificar" style="border: solid 2px #cf74f2;">
             </form>
		</body>
	</html>
	<%} %>